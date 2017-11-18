package action.path;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class DeleteBeBackedUpDirectoryFromList implements ButtonAction{
	
	private Service service=Service.service;
	
	private String beBackUpDirectoryName;
	
	public DeleteBeBackedUpDirectoryFromList() {}
	
	public DeleteBeBackedUpDirectoryFromList(String beBackUpDirectoryName) {
		this.beBackUpDirectoryName = beBackUpDirectoryName;
	}

	public String getBeBackUpDirectoryName() {
		return beBackUpDirectoryName;
	}

	public void setBeBackUpDirectoryName(String beBackUpDirectoryName) {
		this.beBackUpDirectoryName = beBackUpDirectoryName;
	}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String result=service.deleteBeBackUpDirectoryFromList(beBackUpDirectoryName);
		if(result.equals("Success")){
			map.put("ButtonAction", "DeleteBeBackedUpDirectoryFromList");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "DeleteBeBackedUpDirectoryFromList");
			map.put("Result", "Failure");
			map.put("FailureReason", result);
		}
		return map;
	}
}
