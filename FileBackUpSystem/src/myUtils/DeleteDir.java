package myUtils;

import java.io.File;

public class DeleteDir {

	public static boolean deleteDir(File dir) {							//**�ݹ�ɾ���ļ���**//
        if (dir.isDirectory()) {
            String[] children = dir.list();								//�õ����ļ������ļ���
            if(children!=null)
	            for (int i=0; i<children.length; i++) {						//�ݹ�ɾ��Ŀ¼�е���Ŀ¼
	                boolean success = deleteDir(new File(dir, children[i]));//�������ļ����ļ��е�ɾ��
	                if (!success) 
	                    return false;
	            }
        }
        return dir.delete();											// Ŀ¼��ʱΪ�գ�����ɾ��
    }
}
