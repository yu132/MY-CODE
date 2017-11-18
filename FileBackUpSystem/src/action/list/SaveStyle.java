package action.list;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class SaveStyle implements ButtonAction{

	private Service service=Service.service;

	public SaveStyle() {}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		if(service.saveStyle()){
			map.put("ButtonAction", "SaveStyle");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "SaveStyle");
			map.put("Result", "Failure");
		}
		return map;
	}
}
