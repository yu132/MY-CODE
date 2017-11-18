package action.path;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class SetBeBackedUpDirectory implements ButtonAction{
	
	private Service service=Service.service;
	
	private String bbdName;
	
	private Map<String,String> changeMap;

	public SetBeBackedUpDirectory() {}

	public SetBeBackedUpDirectory(String bbdName, Map<String, String> changeMap) {
		this.bbdName = bbdName;
		this.changeMap = changeMap;
	}

	public String getBbdName() {
		return bbdName;
	}

	public void setBbdName(String bbdName) {
		this.bbdName = bbdName;
	}

	public Map<String, String> getChangeMap() {
		return changeMap;
	}

	public void setChangeMap(Map<String, String> changeMap) {
		this.changeMap = changeMap;
	}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String result=service.setBeBackUpDirectory(bbdName, changeMap);
		if(result.equals("Success")){
			map.put("ButtonAction", "SetBeBackedUpDirectory");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "SetBeBackedUpDirectory");
			map.put("Result", "Failure");
			map.put("FailureReason", result);
		}
		return map;
	}
	
}
