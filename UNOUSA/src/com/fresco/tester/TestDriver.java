package com.fresco.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fresco.tester.base.FrescoSearchDownload;
import com.fresco.tester.base.TestBase;
import com.fresco.tester.models.User;

public class TestDriver {

	private int maxInstance;
	private int parllelRunner;
	private Class<? extends TestBase> className;

	ExecutorService executorService = null;
	JSONObject configJson;

	public TestDriver(int maxInstance, int parellel, Class<? extends TestBase> className) throws Exception {
		super();

		File file = new File(System.getenv("CONFIG_PATH"));
		if (!file.exists()) {
			throw new RuntimeException("Config not fount \n\nplease add CONFIG_PATH in out system path ");
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		StringBuilder builder = new StringBuilder();
		String read = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		while ((read = bufferedReader.readLine()) != null) {
			builder.append(read);
		}

		configJson = (JSONObject) new JSONParser().parse(builder.toString());

		this.maxInstance = maxInstance;
		this.parllelRunner = parellel;
		executorService = Executors.newFixedThreadPool(parellel);
		this.className = className;
	}

	public static void main(String[] args) throws Exception {
		ProjectConstants.init();
		TestDriver driver = new TestDriver(2, 2, FrescoSearchDownload.class);
		driver.start();
	}

	private void start() throws Exception {
		for (int i = 0; i < maxInstance; i++) {
			Constructor constructor = className.getConstructor(new Class[] { String.class, String.class, JSONObject.class });
			Pair<String, String> pair = getUserNamePasword(i, maxInstance);
			JSONObject jsonObject = (JSONObject) configJson.get(String.valueOf(i));
			this.executorService.submit((Runnable) constructor.newInstance(pair.frist, pair.secoend, jsonObject));
		}
		executorService.shutdown();
	}

	private Pair<String, String> getUserNamePasword(int current, int max) {
		if (current > ProjectConstants.UserPass.size()) {
			User user = ProjectConstants.UserPass.get(current % ProjectConstants.UserPass.size());
			return new Pair<String, String>(user.getUser(), user.getPassword());
		} else {
			User user = ProjectConstants.UserPass.get(current);
			return new Pair<String, String>(user.getUser(), user.getPassword());
		}
	}

}
