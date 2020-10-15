package Base;

import java.io.FileInputStream;
import java.util.Properties;

public class Base {

	public static String Property(String value) {
		String propertyValue = null;
		try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//Configuration//Config.properties");
		Properties p = new Properties();
		p.load(fis);
		propertyValue = p.getProperty(value);
		fis.close();
		}
		catch (Exception e) {
		}
		return propertyValue;
	}
}
