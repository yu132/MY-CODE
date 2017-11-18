package action.list;

import java.util.HashMap;
import java.util.Map;

import myUtils.ButtonAction;
import service.Service;

public class ChangeStyle implements ButtonAction{

	private Service service=Service.service;
	
	private String styleName;

	public ChangeStyle() {}

	public ChangeStyle(String styleName) {
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
		Map<String, Object> result=service.changeStyle(styleName);
		String res=(String)result.get("RESULT");
		if(res.equals("SUCCESS")){
			map.put("ButtonAction", "ChangeStyle");
			map.put("Result", "Success");
		}else{
			if(res.equals("FAILURE")){
				map.put("ButtonAction", "ChangeStyle");
				map.put("Result", "Failure");
				map.put("FailureReason", result.get("FAILREASON"));
			}else{
				map.put("ButtonAction", "ChangeStyle");
				map.put("Result", "SuccessWithErrors");
				map.put("ErrorReason1", map.get("ERRORREASON1"));
				map.put("ErrorReason2", map.get("ERRORREASON2"));
			}
		}
		return map;
	}
}
