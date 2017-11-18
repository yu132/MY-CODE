package action.list;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import myUtils.ButtonAction;
import service.Service;

public class NewStyle implements ButtonAction{

	private Service service=Service.service;
	
	private String styleName;
	
	private Callable<Boolean> dealWhenStyleFileExist;

	public NewStyle() {}

	public NewStyle(String styleName, Callable<Boolean> dealWhenStyleFileExist) {
		this.styleName = styleName;
		this.dealWhenStyleFileExist = dealWhenStyleFileExist;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	public Callable<Boolean> getDealWhenStyleFileExist() {
		return dealWhenStyleFileExist;
	}

	public void setDealWhenStyleFileExist(Callable<Boolean> dealWhenStyleFileExist) {
		this.dealWhenStyleFileExist = dealWhenStyleFileExist;
	}

	@Override
	public Map<String, Object> execute() {
		Map<String, Object> map=new HashMap<>();
		if(styleName==null){
			map.put("ButtonAction", "NewStyle");
			map.put("Result", "Failure");
			map.put("FailureReason", "Style name can't be null");
		}
		String result=service.newStyle(styleName, dealWhenStyleFileExist);
		if(result.equals("Success")){
			map.put("ButtonAction", "NewStyle");
			map.put("Result", "Success");
		}else{
			map.put("ButtonAction", "NewStyle");
			map.put("Result", "Failure");
			map.put("FailureReason", result);
		}
		return map;
	}
}
