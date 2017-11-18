package action.backup;

import java.util.Map;

import myUtils.ButtonAction;
import service.DoBackService;

public class DoBackup implements ButtonAction{

	DoBackService doBackService=DoBackService.doBackService;
	
	@Override
	public Map<String, Object> execute() {
		return doBackService.doBackup();
	}
} 
