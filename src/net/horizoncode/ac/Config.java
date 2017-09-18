package net.horizoncode.ac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class was created by HorizonCode 
 * Create Date: 18.09.2017 / 21:19 
 * Project: AbductionCounter
 */

public class Config {

	private File file;
	private ConfigList configlist = new ConfigList();

	private Config(File f) {
		file = f;
	}

	private Config(File f, HashMap<String, Object> objectlist) {
		file = f;
		for (String key : objectlist.keySet()) {
			Object value = objectlist.get(key);
			configlist.getList().put(key, value);
		}
	}

	public static Config loadConfig(File f) {
		if (!f.exists()) {
			try {
				f.createNewFile();
				return new Config(f);
			} catch (IOException e) {
			}
		} else {
			HashMap<String, Object> objectList = new HashMap<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));

				String line = "";
				while ((line = br.readLine()) != null) {
					String[] array = line.split(":", 0);
					objectList.put(array[0], array[1]);
					System.out.println(array[0] + " -> " + array[1]);
				}
				br.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			return new Config(f, objectList);
		}
		return null;
	}

	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (String key : configlist.getList().keySet()) {
				Object o = configlist.getList().get(key);
				bw.write(key + ":" + o);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
		}
	}

	public boolean isSet(String key) {
		if (configlist.getList().containsKey(key)) {
			System.out.println("is set -> " + key);
			return true;
		}
		System.out.println("is not set -> " + key);
		return false;
	}

	public void set(String key, Object value) {
		configlist.getList().put(key, value);
		System.out.println(key + " -> " + value);
	}

	public Object get(String key) {
		Object o = configlist.getList().get(key);
		return o;
	}

	public class ConfigList {

		private final HashMap<String, Object> list = new HashMap<String, Object>();

		public HashMap<String, Object> getList() {
			return list;
		}

	}
}
