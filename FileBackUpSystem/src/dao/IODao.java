package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IODao {

	public static String getDataFromFile(File file){						//**从文件中得到信息**//
		byte[] tmp=null;												//储存信息的数组
		FileInputStream fips;											//输入流
		try {
			fips = new FileInputStream(file);							//建立输入流
			tmp = new byte[fips.available()];							//得到数据长度
			fips.read(tmp);												//输入数据
			fips.close();												//关闭输入流
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(tmp!=null)
			return new String(tmp);											//返回输入的信息
		else
			return "";
	}
	
	private static void putDataToFile(File file,String data,boolean cover){	//**向文件中导出信息**//
		try (FileOutputStream fops = new FileOutputStream(file,cover)){//建立输出流，已经以什么方式写入（覆盖或续写）
			fops.write(data.getBytes());								//写入数据
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void putDataToFileFromBegin(File file,String data) {
		putDataToFile(file,data,false);
	}
	
	public static void putDataToFileFromEnd(File file,String data) {
		putDataToFile(file,data,true);
	}
	
}
