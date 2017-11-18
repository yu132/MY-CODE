package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dao.IODao;
import data.DefultPath;
import entity.BeBackUpDirectory;
import myUtils.Counter;

public class DoBackService {

	public final static DoBackService doBackService=new DoBackService();
	
	private Service service=Service.service;
	
	private static boolean doingBackup;
	private static ThreadPoolExecutor threadPool;
	
	static{
		doingBackup=false;
		int cpuNumber=Runtime.getRuntime().availableProcessors();
		threadPool=new ThreadPoolExecutor(0, cpuNumber, 1, TimeUnit.SECONDS , new ArrayBlockingQueue<Runnable>(300), new ThreadPoolExecutor.DiscardPolicy());
	}

	public Map<String, Object> doBackup() {
		Map<String, Object> map=new HashMap<>();
		if(doingBackup){
			map.put("ButtonAction", "DoBackup");
			map.put("Result", "Failure");
			map.put("FailureReason", "Backup is doing now");
			return map;
		}
		List<BeBackUpDirectory> list=service.getBeBackUpDirectoryList();
		if(service.getToPath()!=null){
			if(list.size()!=0){
				List<Future<String[]>> taskResultList=new ArrayList<>(list.size());
				final Counter counter=new Counter(list.size());
				for(final BeBackUpDirectory bbd:list){
					Future<String[]> fu=threadPool.submit(new Callable<String[]>() {
						@Override
						public String[] call() throws Exception {
							String[] array=new String[2];
							try{
								array[0]=bbd.getName();
								long currentTime=System.currentTimeMillis();
								if(bbd.getCycleTime()!=0){
									long cycleMillis=bbd.getCycleTimeUnit().toMillis(bbd.getCycleTime());
									if(currentTime-cycleMillis>=bbd.getLastBackTime()){
										array[1]="There is not enough time interval for one cycle";
										return array;
									}
								}
								File bbdSourceFile=new File(bbd.getLocalPath());
								String toPath;
								if(bbd.getBackupType().equals("Increment"))
									toPath=service.getToPath()+"/"+bbd.getName()+"/"+bbdSourceFile.getName();
								else
									toPath=service.getToPath()+"/"+bbd.getName()+" "+myUtils.GetNowTimeString.getNowTimeString()+"/"+bbdSourceFile.getName();
								System.out.println("To path:"+toPath);
								File file=new File(toPath);
								if(file.exists())
									myUtils.DeleteDir.deleteDir(file);
								if(bbdSourceFile.isDirectory()){
									copyFolder(bbd.getLocalPath(), toPath);
								}else{
									copyFile(bbd.getLocalPath(), toPath);
								}
								array[1]="Success";
								bbd.setLastBackTime(System.currentTimeMillis());
								return array;
							}catch(Exception e){
								array[1]="Error";
								return array;
							}finally{
								counter.countDown();
							}
						}
					});
					new Thread(()->{
						if(counter.isZero())
							doingBackup=false;
							service.saveStyle();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}).start();
					taskResultList.add(fu);
				}
				File data = new File(DefultPath.dataDirectoryPath);
				if(!data.exists())
					data.mkdirs();
				File lastToPathFile = new File(DefultPath.LastBackupPath);
				if (!lastToPathFile.exists()) {
					try {
						lastToPathFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				IODao.putDataToFileFromBegin(lastToPathFile, service.getToPath());;
				map.put("ButtonAction", "DoBackup");
				map.put("Result", "Success");
				map.put("TaskResult", taskResultList);
				map.put("Counter", counter);
				return map;
			}else{
				map.put("ButtonAction", "DoBackup");
				map.put("Result", "Success");
				return map;
			}
		}else{
			map.put("ButtonAction", "DoBackup");
			map.put("Result", "Failure");
			map.put("FailureReason", "To path can't be null when doing backup");
			return map;
		}
	}
	
	/** 
	* 复制整个文件夹内容 
	* @param oldPath String 
	* @param newPath String 
	* @return boolean 
	 * @throws IOException 
	*/ 
	/*private static void copyFolder(String oldPath, String newPath) throws IOException {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}

			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件夹
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	}*/
	
	private static void copyFolder(String oldPath, String newPath) throws IOException {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] files = a.list();
		File temp = null;
		if(files!=null)
			for (int i = 0; i < files.length; i++) {
				if (oldPath.endsWith(File.separator)) 
					temp = new File(oldPath + files[i]);
				else 
					temp = new File(oldPath + File.separator + files[i]);
				if (temp.isFile()) 
					copyFile(temp.getAbsolutePath(),newPath + "/" + (temp.getName()).toString());
				if (temp.isDirectory()) // 如果是子文件夹
					copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
			}
	}

	/** 
	* 复制单个文件 
	* @param oldPath String 原文件路径 如：c:/fqf.txt 
	* @param newPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/ 
	/*private static void copyFile(String oldPath, String newPath) {
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}*/
	
	/**
     * 利用通道copy文件
     * 
     * @param source
     * @param target
     */
    private static void copyFile(String oldPath,String newPath) {
    	File source=new File(oldPath);
    	File target=new File(newPath);
        FileInputStream in = null;
        FileOutputStream out = null;
        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("file not exsits!");
        }
        if (target.exists()) {
            target.delete();
        }
        try {
            target.createNewFile();
            in = new FileInputStream(source);
            out = new FileOutputStream(target);
            FileChannel inChannel = in.getChannel();
            WritableByteChannel outChannel = out.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            outChannel.close();
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
