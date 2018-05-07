package personal.alexgarland.helloworldmvc.service.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManagement {
	
	private static String getPath(String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(fileName).getPath();		
	}

	public static Properties readPropertyFile(String fileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getPath(fileName)));
		}
		catch (IOException ex) {
			// Do not expect this to ever throw as is loading a property file internal to the project.
			// If it does, throw a runtime exception instead and fix source code!
			throw new RuntimeException(ex);
		}
		return properties;
	}
	
}
