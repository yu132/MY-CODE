package action.list;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class ClearStyle implements ButtonAction{

	private Service service=Service.service;

	public ClearStyle() {}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		service.clearStyle();
		map.put("ButtonAction", "SaveStyle");
		map.put("Result", "Success");
		return map;
	}
}
