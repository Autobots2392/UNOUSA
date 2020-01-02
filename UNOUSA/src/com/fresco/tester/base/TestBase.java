package com.fresco.tester.base;

public abstract class TestBase implements Runnable {

	static {
		// System.setProperty("webdriver.Chrome.driver", "F:\\drivers\\chrome
		// driver\\chromedriver.exe");
		// System.setProperty("webdriver.gecko.driver",
		// "F:\\drivers\\gecko\\geckodriver.exe");

		String path = System.getenv("PATH_GEEKO"); 

		System.setProperty("webdriver.gecko.driver", path);

	}

	abstract void startTest() throws Exception;

	public void run() {
		try {
			startTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
