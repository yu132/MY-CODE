package action.list;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class DeleteStyleInDisk implements ButtonAction{

	private Service service=Service.service;
	
	private String styleName;

	public DeleteStyleInDisk() {}

	public DeleteStyleInDisk(String styleName) {
		this.styleName = styleName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		String result=service.deleteStyleInDisk(styleName);;
		if(result.equals("Success")){
			map.put("ButtonAction", "DeleteStyleInDisk");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "DeleteStyleInDisk");
			map.put("Result", "Failure");
			map.put("FailureReason", result);
		}
		return map;
	}
}
