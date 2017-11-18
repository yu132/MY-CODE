package action.path;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class SetToBackupPath implements ButtonAction{

	private Service service=Service.service;
	
	private String path;

	public SetToBackupPath() {}
	public SetToBackupPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		if(service.setToBackupPath(path)){
			map.put("ButtonAction", "SetToBackupPath");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "SetToBackupPath");
			map.put("Result", "Failure");
			map.put("FailureReason", "Illegal path");
		}
		return map;
	}
	
}
