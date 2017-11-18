package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import dao.IODao;
import entity.BeBackUpDirectory;
import myUtils.DoubleList;

public class DataCentralization {

	private Map<String, DoubleList> styleCache = new HashMap<>();// 装载的时候放入
	private Map<String, String[]> pathCache = new HashMap<>();// 预载入的所有切换种类的地址
	private String styleName;// 设定的backup hangonlist的种类选择
	private List<BeBackUpDirectory> beBackedUpDirectoryList;
	private List<BeBackUpDirectory> hangOnDirectoryList;
	private String toPath;

	{
		loadPathCache();
		//System.out.println("Load PathCache finish");
		loadDefultList();
		//System.out.println("Load DefultList finish");
		loadLastToPath();
		//System.out.println("Load LastToPath finish");
		styleName = "Defult";
	}
	
	public void clearStyle(){
		beBackedUpDirectoryList.clear();
		hangOnDirectoryList.clear();
	}
	
	public String getStyleName() {
		return styleName;
	}
	
	public String[] getStylePaths(String styleName){
			return pathCache.get(styleName);
	}
	
	public List<String> getStyleList(){
		List<String> list=new ArrayList<>();
		list.add("Defult");
		for(String key:pathCache.keySet()){
			list.add(key);
		}
		return list;
	}

	public void putPathCache(String name,String path1,String path2){
		String[] tmp=new String[2];
		tmp[0]=path1;
		tmp[1]=path2;
		pathCache.put(name, tmp);
	}
	
	public String deleteStyle(String styleName){
		if(styleName.equals("Defult")){
			return "Deuflt style can't be delete";
		}
		styleCache.remove(styleName);
		String[] tmp=pathCache.remove(styleName);
		savePathCache();
		if(tmp==null)
			return "Style does not exist";
		else
			return "Success";
	}
	
	public String savePathCache(){
		File pathCacheFile = new File(DefultPath.pathCachePath);
		if (!pathCacheFile.exists()){
			try {
				File data = new File(DefultPath.dataDirectoryPath);
				if(!data.exists())
					data.mkdirs();
				pathCacheFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return "Fail to create pathCacheFile";
			}
		}
		StringBuilder sb=new StringBuilder(1000);
		for(Entry<String, String[]> entry : pathCache.entrySet()){
			sb.append(entry.getKey()).append("\t").append(entry.getValue()[0]).append("\t").append(entry.getValue()[1]).append("\r\n");
		}
		IODao.putDataToFileFromBegin(pathCacheFile, sb.toString());
		return "Success";
	}
	
	private void loadLastToPath() {
		File lastToPathFile = new File(DefultPath.LastBackupPath);
		if (lastToPathFile.exists()) {
			toPath = IODao.getDataFromFile(lastToPathFile);
		}
		if(toPath.equals(""))
			toPath="defult_backup";
	}

	public String moveFromBeBackedUpDirectoryListToHangOnDirectoryList(String bbdname){
		BeBackUpDirectory bbd=getBeBackUpDirectoryFromList(bbdname);
		if(bbd==null)
			return "BeBackedUpDirectory does not exist";
		removeBeBackUpDirectoryFromList(bbdname);
		addBeBackUpDirectoryToHangOnList(bbd);
		return "Success";
	}
	
	public String moveFromHangOnDirectoryListToBeBackedUpDirectoryList(String bbdname){
		BeBackUpDirectory bbd=getBeBackUpDirectoryFromHangOnList(bbdname);
		if(bbd==null)
			return "BeBackedUpDirectory does not exist";
		removeBeBackUpDirectoryFromHangOnList(bbdname);
		addBeBackUpDirectoryToList(bbd);
		return "Success";
	}
	
	private void loadPathCache() {
		File pathCacheFile = new File(DefultPath.pathCachePath);
		if (!pathCacheFile.exists())
			return;
		String pathCacheString = IODao.getDataFromFile(pathCacheFile);
		if(pathCacheString.length()!=0){
			String[] pathCacheStringsp = pathCacheString.split("\r\n");
			for (String sp : pathCacheStringsp) {
				String[] spsp = sp.split("\t");
				String[] tmp = new String[2];
				tmp[0] = spsp[1];
				tmp[1] = spsp[2];
				pathCache.put(spsp[0], tmp);
			}
		}
	}

	private void loadDefultList() {
		Map<String, Object> res1=reloadBeBackUpDirectoryList();
		Map<String, Object> res2=reloadHangOnList();
		if(((String)res1.get("RESULT")).equals("FAILURE")||((String)res2.get("RESULT")).equals("FAILURE"))
			createOrRecreateDefultFile();
	}
	
	private void createOrRecreateDefultFile(){
		File floder=new File(DefultPath.defultPath);
		if(!floder.exists())
			floder.mkdirs();
		File defultList=new File(DefultPath.BackedUpListPath);
		File defultHangOnList=new File(DefultPath.HangOnListPath);
		try{
			if(!defultList.exists())
				defultList.createNewFile();
			if(!defultHangOnList.exists())
				defultHangOnList.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Map<String, Object> changeStyle(String styleName) {
		Map<String, Object> map = new HashMap<>();
		if (this.styleName.equals(styleName)) {
			map.put("RESULT", "FAILURE");
			map.put("FAILREASON", "That's the style now");
			return map;
		}
		if(!styleName.equals("Defult")){
			DoubleList doubleList = styleCache.get(styleName);
			if (doubleList == null) {
				String[] stylePath = pathCache.get(styleName);
				if (stylePath == null) {
					map.put("RESULT", "FAILURE");
					map.put("FAILREASON", "Style does not exist");
					return map;
				}
				this.styleName = styleName;
				Map<String, Object> result = reloadBeBackUpDirectoryList(stylePath[0]);
				Map<String, Object> result2 = reloadHangOnList(stylePath[1]);
				styleCache.put(styleName, new DoubleList(beBackedUpDirectoryList, hangOnDirectoryList));
				if (((String) result.get("RESULT")).equals("Success")
						&& ((String) result2.get("RESULT")).equals("Success")) {
					map.put("RESULT", "SUCCESS");
					return map;
				}
				map.put("RESULT", "SUCCESSWITHREEOR");
				map.put("ERRORREASON1", result.get("ERRORREASON"));
				map.put("ERRORREASON2", result2.get("ERRORREASON"));
				return map;
			} else {
				beBackedUpDirectoryList = doubleList.getBeBackedUpDirectoryList();
				hangOnDirectoryList = doubleList.getHangOnDirectoryList();
				this.styleName = styleName;
				map.put("RESULT", "SUCCESS");
				return map;
			}
		}else{
			this.styleName = styleName;
			Map<String, Object> result = reloadBeBackUpDirectoryList();
			Map<String, Object> result2 = reloadHangOnList();
			if (((String) result.get("RESULT")).equals("Success")
					&& ((String) result2.get("RESULT")).equals("Success")) {
				map.put("RESULT", "SUCCESS");
				return map;
			}
			map.put("RESULT", "SUCCESSWITHREEOR");
			map.put("ERRORREASON1", result.get("ERRORREASON"));
			map.put("ERRORREASON2", result2.get("ERRORREASON"));
			return map;
		}
	}

	public boolean isStyleExisting(String styleName) {
		return styleCache.get(styleName) == null ? false : true;
	}

	public List<BeBackUpDirectory> getBeBackUpDirectoryList() {
		return beBackedUpDirectoryList;
	}

	public Map<String, Object> reloadBeBackUpDirectoryList() {
		return reloadBeBackUpDirectoryList(DefultPath.BackedUpListPath);
	}

	private Map<String, Object> reloadBeBackUpDirectoryList(String filePath) {
		Map<String, Object> map = new HashMap<>();
		beBackedUpDirectoryList = new ArrayList<>();
		File BeBackUpDirectoryListFile = new File(filePath);
		List<String> failureReloadList = new ArrayList<>();
		int count = 1;
		boolean flag = false;
		if (!BeBackUpDirectoryListFile.exists()) {
			map.put("RESULT", "FAILURE");
			return map;
		} else {
			String data = IODao.getDataFromFile(BeBackUpDirectoryListFile);
			if(data.length()!=0){
				String[] datasp = data.split("\r\n");
				for (String tmp : datasp) {
					String name = null;
					try {
						String[] tmpsp = tmp.split("\t");
						name = tmpsp[0].split("=")[1];
						long cycleTime = Long.valueOf(tmpsp[1].split("=")[1]);
						TimeUnit cycleTimeUnit = TimeUnit.valueOf(tmpsp[2].split("=")[1]);
						String localPath = tmpsp[3].split("=")[1];
						String backupType = tmpsp[4].split("=")[1];
						long lastBackTime = Long.valueOf(tmpsp[5].split("=")[1]);
						BeBackUpDirectory bbd = new BeBackUpDirectory(name, cycleTime, cycleTimeUnit, localPath, backupType,
								lastBackTime);
						beBackedUpDirectoryList.add(bbd);
						count++;
					} catch (Exception e) {
						e.printStackTrace();
						flag = true;
						if (name != null)
							failureReloadList.add("name:" + name + ";index:" + count);
						else
							failureReloadList.add("index:" + count);
					}
				}
			}
		}
		if (flag) {
			map.put("RESULT", "SUCCESSWITHERROR");
			map.put("ERRORREASON", failureReloadList);
			return map;
		} else {
			map.put("RESULT", "SUCCESS");
			return map;
		}
	}

	public boolean saveBeBackUpDirectoryList(){
		return saveBeBackUpDirectoryList(DefultPath.BackedUpListPath);
	}
	
	public boolean saveBeBackUpDirectoryList(String filePath) {
		try {
			StringBuilder sb = new StringBuilder(1000);
			for (BeBackUpDirectory bbd : beBackedUpDirectoryList) {
				sb.append(bbd.toString());
			}
			File BeBackUpDirectoryListFile = new File(filePath);
			if (!BeBackUpDirectoryListFile.exists())
				BeBackUpDirectoryListFile.createNewFile();
			IODao.putDataToFileFromBegin(BeBackUpDirectoryListFile, sb.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public BeBackUpDirectory getBeBackUpDirectoryFromList(String name) {
		for (BeBackUpDirectory bbd : beBackedUpDirectoryList) {
			if (bbd.getName().equals(name))
				return bbd;
		}
		return null;
	}

	public String addBeBackUpDirectoryToList(BeBackUpDirectory beBackUpDirectory) {
		if (getBeBackUpDirectoryFromList(beBackUpDirectory.getName()) != null||getBeBackUpDirectoryFromHangOnList(beBackUpDirectory.getName()) != null)
			return "Same BeBackUpDirectory's Name";
		String mesg = beBackUpDirectory.check();
		if (mesg.equals("Success"))
			beBackedUpDirectoryList.add(beBackUpDirectory);
		return mesg;
	}

	public String removeBeBackUpDirectoryFromList(String name) {
		BeBackUpDirectory bbd = getBeBackUpDirectoryFromList(name);
		if (bbd != null) {
			beBackedUpDirectoryList.remove(bbd);
			return "Success";
		} else {
			return "BeBackUpDirectory does not exist in list";
		}
	}

	public List<BeBackUpDirectory> getHangOnDirectoryList() {
		return hangOnDirectoryList;
	}

	public BeBackUpDirectory getBeBackUpDirectoryFromHangOnList(String name) {
		for (BeBackUpDirectory bbd : hangOnDirectoryList) {
			if (bbd.getName().equals(name))
				return bbd;
		}
		return null;
	}

	public Map<String, Object> reloadHangOnList() {
		return reloadHangOnList(DefultPath.HangOnListPath);
	}

	private Map<String, Object> reloadHangOnList(String filePath) {
		Map<String, Object> map = new HashMap<>();
		hangOnDirectoryList = new ArrayList<>();
		File hangOnListFile = new File(filePath);
		List<String> failureReloadList = new ArrayList<>();
		int count = 1;
		boolean flag = false;
		if (!hangOnListFile.exists()) {
			map.put("RESULT", "FAILURE");
			return map;
		} else {
			String data = IODao.getDataFromFile(hangOnListFile);
			if(data.length()!=0){
				String[] datasp = data.split("\r\n");
				for (String tmp : datasp) {
					String name = null;
					try {
						String[] tmpsp = tmp.split("\t");
						name = tmpsp[0].split("=")[1];
						long cycleTime = Long.valueOf(tmpsp[1].split("=")[1]);
						TimeUnit cycleTimeUnit = TimeUnit.valueOf(tmpsp[2].split("=")[1]);
						String localPath = tmpsp[3].split("=")[1];
						String backupType = tmpsp[4].split("=")[1];
						long lastBackTime = Long.valueOf(tmpsp[5].split("=")[1]);
						BeBackUpDirectory bbd = new BeBackUpDirectory(name, cycleTime, cycleTimeUnit, localPath, backupType,
								lastBackTime);
						hangOnDirectoryList.add(bbd);
						count++;
					} catch (Exception e) {
						flag = true;
						failureReloadList.add("name:" + name + ";index:" + count);
					}
				}
			}
		}
		if (flag) {
			map.put("RESULT", "SUCCESSWITHERROR");
			map.put("ERRORREASON", failureReloadList);
			return map;
		} else {
			map.put("RESULT", "SUCCESS");
			return map;
		}
	}

	public boolean saveHangOnList() {
		return saveHangOnList(DefultPath.HangOnListPath);
	}
	
	public boolean saveHangOnList(String filePath) {
		try {
			StringBuilder sb = new StringBuilder(1000);
			for (BeBackUpDirectory bbd : hangOnDirectoryList) {
				sb.append(bbd.toString());
			}
			File HangOnListFile = new File(filePath);
			if (!HangOnListFile.exists())
				HangOnListFile.createNewFile();
			IODao.putDataToFileFromBegin(HangOnListFile, sb.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String addBeBackUpDirectoryToHangOnList(BeBackUpDirectory beBackUpDirectory) {
		if (getBeBackUpDirectoryFromList(beBackUpDirectory.getName())!= null||getBeBackUpDirectoryFromHangOnList(beBackUpDirectory.getName())!= null)
			return "Same BeBackUpDirectory's Name";
		String mesg = beBackUpDirectory.check();
		if (mesg.equals("Success"))
			hangOnDirectoryList.add(beBackUpDirectory);
		return mesg;
	}

	public String removeBeBackUpDirectoryFromHangOnList(String name) {
		BeBackUpDirectory bbd = getBeBackUpDirectoryFromHangOnList(name);
		if (bbd != null) {
			hangOnDirectoryList.remove(bbd);
			return "Success";
		} else {
			return "BeBackUpDirectory does not exist in HangOnToList";
		}

	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}

}
