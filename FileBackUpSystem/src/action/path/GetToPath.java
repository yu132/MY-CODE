package action.path;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class GetToPath implements ButtonAction{
	
	private Service service=Service.service;
	
	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String toPath=service.getToPath();
		map.put("ButtonAction", "NewStyle");
		map.put("Result", "Success");
		map.put("ToPath", toPath);
		return map;
	}
}
