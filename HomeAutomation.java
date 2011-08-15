import java.io.FileInputStream;
import java.util.Properties;

import com.matthewhuntington.homeautomation.*;

public class HomeAutomation
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream("login.properties"));
			CalendarParser parser = new CalendarParser(properties.getProperty("calendar"), properties.getProperty("user"), properties.getProperty("pass"));
			ActionExecutor executor = new ActionExecutor(parser.getActions());
			executor.executeAll();
		}
		catch (Exception e)
		{
			System.out.println("Could not load properties file.");
		}
	}
}