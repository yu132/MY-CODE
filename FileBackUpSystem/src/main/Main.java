package main;

import ui.cliUI.CliUI;

public class Main {
	
	public static void main(String[] args) {
		if(args.length==0);
		else{
			if(args.length==1){
				if(args[0].equals("-cli")){
					CliUI.useCommandLineUI();
				}
			}
		}
	}
	
	//��һ�������޸Ľ����Ĭ��style�ķ���
	//����������˺ںв��ԣ�����
	
	//np testpath 0 seconds d:\test Increment 0
	//stp d:\testbackup
	//bu

}
