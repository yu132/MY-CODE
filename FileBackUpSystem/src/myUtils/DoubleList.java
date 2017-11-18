package myUtils;

import java.util.List;

import entity.BeBackUpDirectory;

public class DoubleList {

	private List<BeBackUpDirectory> beBackedUpDirectoryList;
	private List<BeBackUpDirectory> hangOnDirectoryList;
	
	public DoubleList() {}
	
	public DoubleList(List<BeBackUpDirectory> beBackedUpDirectoryList, List<BeBackUpDirectory> hangOnDirectoryList) {
		this.beBackedUpDirectoryList = beBackedUpDirectoryList;
		this.hangOnDirectoryList = hangOnDirectoryList;
	}
	
	public List<BeBackUpDirectory> getBeBackedUpDirectoryList() {
		return beBackedUpDirectoryList;
	}
	public void setBeBackedUpDirectoryList(List<BeBackUpDirectory> beBackedUpDirectoryList) {
		this.beBackedUpDirectoryList = beBackedUpDirectoryList;
	}
	public List<BeBackUpDirectory> getHangOnDirectoryList() {
		return hangOnDirectoryList;
	}
	public void setHangOnDirectoryList(List<BeBackUpDirectory> hangOnDirectoryList) {
		this.hangOnDirectoryList = hangOnDirectoryList;
	}
	
}
