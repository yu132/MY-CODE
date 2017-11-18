package entity;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BeBackUpDirectory {

	private String name;
	private long cycleTime;
	private TimeUnit cycleTimeUnit;
	private String localPath;
	private String backupType;
	private long lastBackTime;
	
	public BeBackUpDirectory() {}

	public BeBackUpDirectory(String name, long cycleTime, TimeUnit cycleTimeUnit, String localPath, String backupType,
			long lastBackTime) {
		this.name = name;
		this.cycleTime = cycleTime;
		this.cycleTimeUnit = cycleTimeUnit;
		this.localPath = localPath;
		this.backupType = backupType;
		this.lastBackTime = lastBackTime;
	}

	public String check(){
		if(name==null)
			return "Name can't be null";
		if(cycleTime!=0&&cycleTimeUnit==null)
			return "CycleTimeUnit can't be null when cycleTime is not 0";
		if(localPath==null)
			return "LocalPath can't be null";
		if(!backupType.equals("Increment")&&!backupType.equals("Time"))
			return "BackupType should be Increment or Time";
		return "Success";
	}
	
	public static String checkPartOfArguments(Map<String,String> changeMap,BeBackUpDirectory bbd){
		String name=bbd.name;
		String localPath=bbd.localPath;
		long cycleTime=bbd.cycleTime;
		TimeUnit cycleTimeUnit=bbd.cycleTimeUnit;
		String backupType=bbd.backupType;
		for (Map.Entry<String, String> entry : changeMap.entrySet()) {
			switch(entry.getKey()){
			case "name":
				name=entry.getValue();
				break;
			case "cycleTime":
				cycleTime=Long.valueOf(entry.getValue());
				break;
			case "cycleTimeUnit":
				cycleTimeUnit=TimeUnit.valueOf(entry.getValue());
				break;
			case "localPath":
				localPath=entry.getValue();
				break;
			case "backupType":
				backupType=entry.getValue();
				break;
			}
		}
		if(name==null)
			return "Name can't be null";
		if(cycleTime!=0&&cycleTimeUnit==null)
			return "CycleTimeUnit can't be null when cycleTime is not 0";
		if(localPath==null)
			return "LocalPath can't be null";
		if(!backupType.equals("Increment")&&!backupType.equals("Time"))
			return "BackupType should be Increment or Time";
		return "Success";
	}
	
	@Override
	public String toString() {
		return "name=" + name + "\tcycleTime=" + cycleTime + "\tcycleTimeUnit=" + cycleTimeUnit
				+ "\tlocalPath=" + localPath +"\tbackupType="+backupType+ "\tlastBackTime=" + lastBackTime + "\r\n";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCycleTime() {
		return cycleTime;
	}
	public void setCycleTime(long cycleTime) {
		this.cycleTime = cycleTime;
	}
	public TimeUnit getCycleTimeUnit() {
		return cycleTimeUnit;
	}
	public void setCycleTimeUnit(TimeUnit cycleTimeUnit) {
		this.cycleTimeUnit = cycleTimeUnit;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getBackupType() {
		return backupType;
	}
	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}
	public long getLastBackTime() {
		return lastBackTime;
	}
	public void setLastBackTime(long lastBackTime) {
		this.lastBackTime = lastBackTime;
	}
}
