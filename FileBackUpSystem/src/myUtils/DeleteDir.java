package myUtils;

import java.io.File;

public class DeleteDir {

	public static boolean deleteDir(File dir) {							//**递归删除文件夹**//
        if (dir.isDirectory()) {
            String[] children = dir.list();								//得到子文件或子文件夹
            if(children!=null)
	            for (int i=0; i<children.length; i++) {						//递归删除目录中的子目录
	                boolean success = deleteDir(new File(dir, children[i]));//进行子文件或文件夹的删除
	                if (!success) 
	                    return false;
	            }
        }
        return dir.delete();											// 目录此时为空，可以删除
    }
}
