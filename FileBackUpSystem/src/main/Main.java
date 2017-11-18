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
	
	//加一个可以修改进入的默认style的方法
	//基本上完成了黑盒测试！！！
	
	//np testpath 0 seconds d:\test Increment 0
	//stp d:\testbackup
	//bu

}
