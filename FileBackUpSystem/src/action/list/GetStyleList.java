package action.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class GetStyleList implements ButtonAction{

	private Service service=Service.service;
	
	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		List<String> list=service.getStyleList();
		map.put("ButtonAction", "NewStyle");
		map.put("Result", "Success");
		map.put("DataList", list);
		return map;
	}
}
