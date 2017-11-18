package action.list;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class GetNowStyle implements ButtonAction{
	private Service service=Service.service;
	
	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String style=service.getNowStyle();
		map.put("ButtonAction", "NewStyle");
		map.put("Result", "Success");
		map.put("Style", style);
		return map;
	}
}
