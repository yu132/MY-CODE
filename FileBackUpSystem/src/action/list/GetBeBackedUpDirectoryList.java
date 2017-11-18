package action.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.BeBackUpDirectory;
import myUtils.ButtonAction;
import service.Service;

public class GetBeBackedUpDirectoryList implements ButtonAction{

	private Service service=Service.service;

	public GetBeBackedUpDirectoryList() {}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		List<BeBackUpDirectory> list=service.getBeBackUpDirectoryList();
		map.put("ButtonAction", "GetBeBackedUpDirectoryList");
		map.put("Result", "Success");
		map.put("DataList", list);
		return map;
	}
}
