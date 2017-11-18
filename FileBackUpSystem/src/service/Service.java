package service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import dao.IODao;
import data.DataCentralization;
import data.DefultPath;
import entity.BeBackUpDirectory;

public class Service {
	
	public final static Service service=new Service();
	
	private DataCentralization dc=new DataCentralization();

	public String getNowStyle(){
		return dc.getStyleName();
	}
	
	public List<String> getStyleList(){
		return dc.getStyleList();
	}
	
	public List<BeBackUpDirectory> getBeBackUpDirectoryList(){
		return dc.getBeBackUpDirectoryList();
	}
	
	public List<BeBackUpDirectory> getHangOnList(){
		return dc.getHangOnDirectoryList();
	}
	
	public String newStyle(String styleName,Callable<Boolean> dealWhenStyleFileExist){
		if(dc.isStyleExisting(styleName)){
			return "Style exists";
		}else{
			File pathCacheFile = new File(DefultPath.pathCachePath);
			if (!pathCacheFile.exists()){
				try {
					File data = new File(DefultPath.dataDirectoryPath);
					if(!data.exists())
						data.mkdirs();
					pathCacheFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return "Fail to create pathCacheFile";
				}
			}
			File folder=new File("data/style/"+styleName);
			folder.mkdirs();
			String BackedUpListPath="data/style/"+styleName+"/"+styleName+"_BackedUpList.txt";
			String HangOnListPath="data/style/"+styleName+"/"+styleName+"_HangOnList.txt";
			String data=styleName+"\t"+BackedUpListPath+"\t"+HangOnListPath;
			IODao.putDataToFileFromEnd(pathCacheFile, data);
			File BackedUpListFile=new File(BackedUpListPath);
			File HangOnListFile=new File(HangOnListPath);
			if(!BackedUpListFile.exists()&&!HangOnListFile.exists()){
				try {
					BackedUpListFile.createNewFile();
					HangOnListFile.createNewFile();
				} catch (IOException e) {
					return "Success with file create error";
				}
			}else{
				boolean tmp=false;//true代表删除，fasle代表留下继续使用
				try {
					tmp=dealWhenStyleFileExist.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(tmp){
					try {
						BackedUpListFile.delete();
						BackedUpListFile.createNewFile();
						HangOnListFile.delete();
						HangOnListFile.createNewFile();
					} catch (IOException e) {
						return "Success with file create error";
					}
				}
			}
			dc.putPathCache(styleName, BackedUpListPath, HangOnListPath);
			return "Success";
		}
	}
	
	public String deleteStyleInMenu(String styleName){
		return dc.deleteStyle(styleName);
	}
	
	public String deleteStyleInDisk(String styleName){
		String res=dc.deleteStyle(styleName);
		if(res.equals("Success"))
			myUtils.DeleteDir.deleteDir(new File("data/style/"+styleName));
		return res;
	}
	
	public Map<String, Object> changeStyle(String styleName){
		return dc.changeStyle(styleName);
	}
	
	public boolean saveStyle(){
		String styleName=getNowStyle();
		if(styleName.equals("Defult")){
			boolean res1=dc.saveBeBackUpDirectoryList();
			boolean res2=dc.saveHangOnList();
			return res1&&res2;
		}else{
			String[] paths=dc.getStylePaths(styleName);
			String path1=paths[0];
			String path2=paths[1];
			
			boolean res1=dc.saveBeBackUpDirectoryList(path1);
			boolean res2=dc.saveHangOnList(path2);
			return res1&&res2;
		}
	}
	
	public String moveToBeBackedUpDirectoryList(String beBackUpDirectoryName){
		return dc.moveFromHangOnDirectoryListToBeBackedUpDirectoryList(beBackUpDirectoryName);
	}
	
	public String moveToHangOnList(String beBackUpDirectoryName){
		return dc.moveFromBeBackedUpDirectoryListToHangOnDirectoryList(beBackUpDirectoryName);
	}
	
	public void newBeBackUpDirectoryToList(BeBackUpDirectory beBackUpDirectory){
		dc.addBeBackUpDirectoryToList(beBackUpDirectory);
	}
	
	public String deleteBeBackUpDirectoryFromList(String beBackUpDirectoryName){
		return dc.removeBeBackUpDirectoryFromList(beBackUpDirectoryName);
	}
	
	public String deleteBeBackUpDirectoryFromHangOnList(String beBackUpDirectoryName){
		return dc.removeBeBackUpDirectoryFromHangOnList(beBackUpDirectoryName);
	}
	
	public boolean setToBackupPath(String path){
		if(dc!=null){
			File file=new File(path);
			if(file.isDirectory()){
				dc.setToPath(path);
				return true;
			}else
				return false;
		}else
			return false;
	}
	
	public String setBeBackUpDirectory(String bbdName,Map<String,String> changeMap){
		BeBackUpDirectory bbd=dc.getBeBackUpDirectoryFromList(bbdName);
		if(bbd!=null){
			String changeName=changeMap.get("name");
			BeBackUpDirectory temp=dc.getBeBackUpDirectoryFromList(changeName);
			if(temp!=null)
				return "Same beBackUpDirectory name";
			String check=BeBackUpDirectory.checkPartOfArguments(changeMap, bbd);
			if(check.equals("Success"))
				for (Map.Entry<String, String> entry : changeMap.entrySet()) {
					switch(entry.getKey()){
					case "name":
						bbd.setName(entry.getValue());
						break;
					case "cycleTime":
						bbd.setCycleTime(Long.valueOf(entry.getValue()));
						break;
					case "cycleTimeUnit":
						bbd.setCycleTimeUnit(TimeUnit.valueOf(entry.getValue()));
						break;
					case "localPath":
						bbd.setLocalPath(entry.getValue());
						break;
					case "backupType":
						bbd.setBackupType(entry.getValue());
						break;
					case "lastBackTime":
						bbd.setLastBackTime(Long.valueOf(entry.getValue()));
						break;
					}
				}
			return check;
		}else
			return "BeBackUpDirectory does not exist";
	}

	public String getToPath(){
		return dc.getToPath();
	}
	
	public int getBeBackUpDirectoryListSize(){
		return dc.getBeBackUpDirectoryList().size();
	}

	public void clearStyle(){
		dc.clearStyle();
	}
}
