package action.path;

import java.util.HashMap;
import java.util.Map;

import entity.BeBackUpDirectory;
import myUtils.ButtonAction;
import service.Service;

public class NewBeBackedUpDirectory implements ButtonAction{

	private Service service=Service.service;
	
	private BeBackUpDirectory beBackUpDirectory;
	
	public NewBeBackedUpDirectory() {}
	
	public NewBeBackedUpDirectory(BeBackUpDirectory beBackUpDirectory) {
		this.beBackUpDirectory = beBackUpDirectory;
	}

	public BeBackUpDirectory getBeBackUpDirectory() {
		return beBackUpDirectory;
	}

	public void setBeBackUpDirectory(BeBackUpDirectory beBackUpDirectory) {
		this.beBackUpDirectory = beBackUpDirectory;
	}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String result=beBackUpDirectory.check();
		if(result.equals("Success")){
			service.newBeBackUpDirectoryToList(beBackUpDirectory);
			map.put("ButtonAction", "NewBeBackedUpDirectory");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "NewBeBackedUpDirectory");
			map.put("Result", "Failure");
			map.put("FailureReason", result);
		}
		return map;
	}
	
}
