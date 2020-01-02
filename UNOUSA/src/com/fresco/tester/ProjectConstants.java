package com.fresco.tester;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fresco.tester.models.User;

import jxl.Sheet;
import jxl.Workbook;

public class ProjectConstants {

	public static final String TEST_END_POINT = "https://uno.frescodata.com/login.php";
	public static final List<User> UserPass;

	static {
		UserPass = new ArrayList<User>();
		try {
			/*
			 * String path = System.getProperty("user.dir"); // return project folder path
			 * String Filepath = path + "\\Testdata\\Testdata.xls"; // return driver folder
			 * path
			 * 
			 * FileInputStream fi = new FileInputStream(Filepath);
			 */
			InputStream in = ProjectConstants.class.getClassLoader()
					.getResourceAsStream("com/fresco/tester/Testdata.xls");
			Sheet s;
			Workbook w = Workbook.getWorkbook(in);
			s = w.getSheet(0);

			for (int row = 0; row < s.getRows(); row++) {
				String username = s.getCell(0, row).getContents();
				String password = s.getCell(1, row).getContents();
				User user = new User(username, password);
				UserPass.add(user);
				System.out.println("added user = " + username + " password = " + password);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable To read config file");
		}
	}

	public static void init() {

	}

}
