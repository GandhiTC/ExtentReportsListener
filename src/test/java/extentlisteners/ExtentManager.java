package extentlisteners;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager
{
	private static ExtentReports extent;


	public static ExtentReports createInstance(String fileName)
	{
		String css	  = "details {\r\n" +
						"  font: \"Open Sans\", Calibri, sans-serif;\r\n" +
						"  width: 100%;\r\n" +
						"}\r\n" +
						"\r\n" +
						"details > summary {\r\n" +
						"  color: white;\r\n" +
						"  padding: 2px 6px;\r\n" +
						"  width: 18em;\r\n" +
						"  background-color: FireBrick;\r\n" +
						"  border: none;\r\n" +
						"  box-shadow: 3px 3px 4px black;\r\n" +
						"  cursor: pointer;\r\n" +
						"  font-size: 1.25em;\r\n" +
						"  font-weight: bold;\r\n" +
						"  display: list-item;\r\n" +
						"  border-radius: 5px 5px 0 0;\r\n" +
						"}\r\n" +
						"\r\n" +
						"details > p {\r\n" +
						"  border-radius: 0 0 10px 10px;\r\n" +
						"  background-color: Salmon;\r\n" +
						"  padding: 2px 6px;\r\n" +
						"  margin: 0;\r\n" +
						"  box-shadow: 3px 3px 4px black;\r\n" +
						"}\r\n" +
						"\r\n" +
						".tabbed {\r\n" +
						"	display:inline-block;\r\n" +
						"    width:30px;\r\n" +
						"}";
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setCSS(css);
		
		//	as an alternative to using htmlReporter.config() code lines, the extent-config.xml file could be loaded instead
//		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Tejas Gandhi");
		extent.setSystemInfo("Orgainzation", "com.github.GandhiTC");
		extent.setSystemInfo("Build No", "TG00-000x");
		
		return extent;
	}
	
	
	/*
	public static String	screenshotPath;
	public static String	screenshotName;


	public static void captureScreenshot()
	{
		File	scrFile	= ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		Date	d		= new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try
		{
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	*/
}
