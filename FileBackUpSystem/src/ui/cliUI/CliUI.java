package ui.cliUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import action.backup.DoBackup;
import action.list.ChangeStyle;
import action.list.ClearStyle;
import action.list.DeleteStyleInDisk;
import action.list.DeleteStyleInMenu;
import action.list.GetBeBackedUpDirectoryList;
import action.list.GetHangOnList;
import action.list.GetNowStyle;
import action.list.GetStyleList;
import action.list.NewStyle;
import action.list.SaveStyle;
import action.list.move.MoveToBeBackUpDirectoryList;
import action.list.move.MoveToHangOnList;
import action.path.DeleteBeBackedUpDirectoryFromHangOnList;
import action.path.DeleteBeBackedUpDirectoryFromList;
import action.path.GetToPath;
import action.path.NewBeBackedUpDirectory;
import action.path.SetBeBackedUpDirectory;
import action.path.SetToBackupPath;
import entity.BeBackUpDirectory;
import myUtils.Counter;

public class CliUI {

	public static void useCommandLineUI(){
		try(Scanner scan=new Scanner(System.in)){
			System.out.println("Welcome to use backup system!");
			while(true){
				System.out.print("Please input you command:");
				String cmd=scan.nextLine();
				if(executeCmd(cmd,scan))
					break;
			}
		}
	}

	private static boolean executeCmd(String cmd,Scanner scan){
		String[] cmdsp=cmd.split(" ");
		Map<String, Object> result;
		String result2;
		switch(cmdsp[0]){
		case "db":
		case "dobackup":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'bc\' or \'dobackup\'");
				break;
			}
			if(service.Service.service.getBeBackUpDirectoryListSize()!=0){
				result=new DoBackup().execute();
				result2=(String) result.get("Result");
				if(result2.equals("Result:Failure")){
					System.out.println("Failure");
					System.out.println("FailureReason:"+(String)result.get("FailureReason"));
				}
				Counter counter=(Counter) result.get("Counter");
				@SuppressWarnings("unchecked") 
				List<Future<String[]>> taskResultList=(List<Future<String[]>>) result.get("TaskResult");
				printProgressBar(0);
				int last=0;
				System.out.println("-------------------------------------------------------------------------------------------------------------");
				System.out.print("progress:");
				while(true){
					double finish=(counter.getAll()-counter.getCount())/counter.getAll();
					int now=(int)(100*finish)/1;
					int dif=now-last;
					last=now;
					printProgressBar(dif);
					if(finish==1)
						break;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------------------");
				for(Future<String[]> taskFuture:taskResultList){
					try {
						String[] taskResult=taskFuture.get();
						System.out.println("taskname="+taskResult[0]+" result:"+taskResult[1]);
					} catch (InterruptedException | ExecutionException e) {
						System.out.println("Error");
					}
				}
				System.out.println("--------------------------");
				System.out.println("Dobackup finish!");
			}else
				System.out.println("Backup list is empty!");
			break;
		case "ns":
		case "newstyle":
			if(cmdsp.length!=2){
				if(cmdsp.length>2)
					System.out.println("Illegal command:Too many arguments to \'ns\' or \'newstyle\'");
				else
					System.out.println("Illegal command:Too little arguments to \'ns\' or \'newstyle\'");
				break;
			}
			result=new NewStyle(cmdsp[1],new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					System.out.println("Some style with the same name has existed in disk!");
					while(true){
						System.out.println("Delete it or use it?y/n(Y/N)");
						String ans=scan.nextLine();
						if(ans.equals("Y")||ans.equals("y"))
							return true;
						else if(ans.equals("N")||ans.equals("n")){
							return false;
						}else
							System.out.println("Illegal input");
					}
				}
			}).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "ss":
		case "savestyle":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'ss\' or \'savestyle\'");
				break;
			}
			result=new SaveStyle().execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "ds":
		case "deletestyle":
			if(cmdsp.length==2){
				result=new DeleteStyleInDisk(cmdsp[1]).execute();
			}else if(cmdsp.length==3){
				if(cmdsp[1].equals("-im")&&cmdsp[1].equals("-inmenu"))
					result=new DeleteStyleInMenu(cmdsp[2]).execute();
				else if(cmdsp[1].equals("-id")&&cmdsp[1].equals("-indisk"))
					result=new DeleteStyleInDisk(cmdsp[2]).execute();
				else{
					System.out.println("Illegal command:Illegal arguments to \'mv\' or \'movepathto\'");
					System.out.println("Result:Failure");
					break;
				}
			}else{
				if(cmdsp.length>3)
					System.out.println("Illegal command:Too many arguments to \'ds\' or \'deletestyle\'");
				else if(cmdsp.length<2)
					System.out.println("Illegal command:Too little arguments to \'ds\' or \'deletestyle\'");
				break;
			}
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "cs":
		case "changestyle":
			if(cmdsp.length!=2){
				if(cmdsp.length>2)
					System.out.println("Illegal command:Too many arguments to \'cs\' or \'changestyle\'");
				else
					System.out.println("Illegal command:Too little arguments to \'cs\' or \'changestyle\'");
				break;
			}
			result=new ChangeStyle(cmdsp[1]).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "cls":
		case "clearstyle":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'cls\' or \'clearstyle\'");
				break;
			}
			new ClearStyle().execute();
			System.out.println("Result:Success");
			break;
		case "sl":
		case "stylelist":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'sl\' or \'stylelist\'");
				break;
			}
			result=new GetStyleList().execute();
			@SuppressWarnings("unchecked")
			List<String> list=(List<String>) result.get("DataList");
			int count=1;
			System.out.println("----------");
			for(String styleName:list){
				System.out.println(count+". "+styleName);
				count++;
			}
			System.out.println("----------");
			System.out.println("Result:Success");
			break;
		case "s":
		case "style":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'s\' or \'style\'");
				break;
			}
			result=new GetNowStyle().execute();
			String style=(String) result.get("Style");
			System.out.println("Style name:"+style);
			System.out.println("Result:Success");
			break;
		case "l":
		case "list":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'l\' or \'list\'");
				break;
			}
			result=new GetBeBackedUpDirectoryList().execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
			}else{
				@SuppressWarnings("unchecked")
				List<BeBackUpDirectory> list2=(List<BeBackUpDirectory>) result.get("DataList");
				System.out.println("----------");
				for(BeBackUpDirectory bbd:list2){
					System.out.print(bbd.toString());
				}
				if(list2.size()==0)
					System.out.println("There is not path!");
				System.out.println("----------");
				System.out.println("Result:Success");
			}
			break;
		case "hl":
		case "hangonlist":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'hl\' or \'hangonlist\'");
				break;
			}
			result=new GetHangOnList().execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
			}else{
				@SuppressWarnings("unchecked")
				List<BeBackUpDirectory> list2=(List<BeBackUpDirectory>) result.get("DataList");
				System.out.println("----------");
				for(BeBackUpDirectory bbd:list2){
					System.out.print(bbd.toString());
				}
				if(list2.size()==0)
					System.out.println("There is not path!");
				System.out.println("----------");
				System.out.println("Result:Success");
			}
			break;
		case "np":
		case "newpath":
			if(cmdsp.length!=7){
				if(cmdsp.length>7)
					System.out.println("Illegal command:Too many arguments to \'np\' or \'newpath\'");
				else
					System.out.println("Illegal command:Too little arguments to \'np\' or \'newpath\'");
				break;
			}
			try{
				result=new NewBeBackedUpDirectory(new BeBackUpDirectory(cmdsp[1],Long.valueOf(cmdsp[2]),cmdsp[3].equals("null")?null:TimeUnit.valueOf(cmdsp[3].toUpperCase()),cmdsp[4],cmdsp[5],Long.valueOf(cmdsp[6]))).execute();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Result:Failure");
				System.out.println("FailureReason:Illegal arguments");
				break;
			}
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "dp":
		case "deletepath":
			if(cmdsp.length!=2){
				if(cmdsp.length>2)
					System.out.println("Illegal command:Too many arguments to \'dp\' or \'deletepath\'");
				else
					System.out.println("Illegal command:Too little arguments to \'dp\' or \'deletepath\'");
				break;
			}
			result=new DeleteBeBackedUpDirectoryFromList(cmdsp[1]).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "dph":
		case "deletepathinhangonlist":
			if(cmdsp.length!=2){
				if(cmdsp.length>2)
					System.out.println("Illegal command:Too many arguments to \'dph\' or \'deletepathinhangonlist\'");
				else
					System.out.println("Illegal command:Too little arguments to \'dph\' or \'deletepathinhangonlist\'");
				break;
			}
			result=new DeleteBeBackedUpDirectoryFromHangOnList(cmdsp[1]).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "cp":
		case "changepath":
			if(cmdsp.length<3||cmdsp.length>9){
				if(cmdsp.length>9)
					System.out.println("Illegal command:Too many arguments to \'cp\' or \'changepath\'");
				else
					System.out.println("Illegal command:Too little arguments to \'cp\' or \'changepath\'");
				break;
			}
			String bbdName=cmdsp[1];
			Map<String,String> map=new HashMap<>();
			for(int i=2;i<cmdsp.length;i++){
				String[] cmdspsp=cmdsp[2].split("=");
				map.put(cmdspsp[0], cmdspsp[1]);
			}
			result=new SetBeBackedUpDirectory(bbdName,map).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "mv":
		case "movepathto":
			if(cmdsp.length<2||cmdsp.length>3){
				if(cmdsp.length>3)
					System.out.println("Illegal command:Too many arguments to \'mv\' or \'movepathto\'");
				else
					System.out.println("Illegal command:Too little arguments to \'mv\' or \'movepathto\'");
				break;
			}
			if(cmdsp.length==2||cmdsp[2].equals("-lth"))
				result=new MoveToHangOnList(cmdsp[1]).execute();
			else if(cmdsp[2].equals("-htl"))
				result=new MoveToBeBackUpDirectoryList(cmdsp[1]).execute();
			else{
				System.out.println("Illegal command:Illegal arguments to \'mv\' or \'movepathto\'");
				System.out.println("Result:Failure");
				break;
			}
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "stp":
		case "settopath":
			if(cmdsp.length!=2){
				if(cmdsp.length>2)
					System.out.println("Illegal command:Too many arguments to \'stp\' or \'settopath\'");
				else
					System.out.println("Illegal command:Too little arguments to \'stp\' or \'settopath\'");
				break;
			}
			result=new SetToBackupPath(cmdsp[1]).execute();
			result2=(String) result.get("Result");
			if(result2.equals("Failure")){
				System.out.println("Result:Failure");
				System.out.println("FailureReason:"+(String)result.get("FailureReason"));
			}else{
				System.out.println("Result:Success");
			}
			break;
		case "tp":
		case "topath":
			if(cmdsp.length!=1){
				System.out.println("Illegal command:Too many arguments to \'tp\' or \'topath\'");
				break;
			}
			result=new GetToPath().execute();
			String toPath=(String) result.get("ToPath");
			System.out.println("To path:"+toPath);
			System.out.println("Result:Success");
			break;
		case "cui":
		case "changeui":
			//TODO 写好swing再说，现在和quit没啥区别。。。
			return true;
		case "q":
		case "quit":
			System.out.println("Thanks for using!");
			return true;
		default:
			System.out.println("Illegal command:Unkonw command");
		}
		return false;
	}
	
	private static void printProgressBar(int a){
		for(int i=0;i<a;i++){
			System.out.print("■");
		}
	}
	
	/*public static void main(String[] args) {
		NumberFormat num = NumberFormat.getPercentInstance(); 
		num.setMaximumIntegerDigits(3); 
		num.setMaximumFractionDigits(2); 
		double csdn = 0.205; 
		System.out.println(num.format(csdn));
	}*/
}
