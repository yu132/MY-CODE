package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IODao {

	public static String getDataFromFile(File file){						//**���ļ��еõ���Ϣ**//
		byte[] tmp=null;												//������Ϣ������
		FileInputStream fips;											//������
		try {
			fips = new FileInputStream(file);							//����������
			tmp = new byte[fips.available()];							//�õ����ݳ���
			fips.read(tmp);												//��������
			fips.close();												//�ر�������
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(tmp!=null)
			return new String(tmp);											//�����������Ϣ
		else
			return "";
	}
	
	private static void putDataToFile(File file,String data,boolean cover){	//**���ļ��е�����Ϣ**//
		try (FileOutputStream fops = new FileOutputStream(file,cover)){//������������Ѿ���ʲô��ʽд�루���ǻ���д��
			fops.write(data.getBytes());								//д������
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
