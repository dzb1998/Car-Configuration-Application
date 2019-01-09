package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import adapter.BuildAuto;
import exception.AutoException;

public class CarModelOptionsIO {

	/**
	 * Read property file.
	 * @param fileName file name
	 * @return properties object
	 */
	public static Properties readPropFile(String fileName) {
		Properties prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			prop.load(in);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop;
	}

	/**
	 * Receive responses.
	 * @param response response
	 */
	public static void receiveResponse(String response) {
		if (response.contains("successfully")) {
			System.out.println("Building successfully.");
		} else {
			System.out.println("Building failed.");
		}
	}

	/**
	 * Build Automobile and handle different type of files.
	 * @param fileName file name
	 * @param fileType file type
	 */
	public void buildAuto(String fileName, int fileType) {
		BuildAuto build = new BuildAuto();
		try {
			build.buildAuto(fileName, fileType);
		} catch (AutoException e) {
			build.fix(e);
		}
	}
}
