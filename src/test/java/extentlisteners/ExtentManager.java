package extentlisteners;



import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager
{
	private static ExtentReports extent;


	public static ExtentReports createInstance()
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
		
		String 				userDir 			= System.getProperty("user.dir");
		Path 				userDirPath 		= Paths.get(userDir);
		String 				projectName			= userDirPath.getFileName().toString();
		String				reportName			= "Test Results for Project: " + projectName;
		
		Date				currentDate			= new Date();
		
		SimpleDateFormat 	simpleDateFormat	= new SimpleDateFormat("yyyy");
		String				yearFolder			= simpleDateFormat.format(currentDate);
		
							simpleDateFormat	= new SimpleDateFormat("MMM");
		String				monthFolder			= simpleDateFormat.format(currentDate);
		
							simpleDateFormat	= new SimpleDateFormat("dd-MMM-yyyy");
		String				dayFolder			= simpleDateFormat.format(currentDate);
		
							simpleDateFormat	= new SimpleDateFormat("hh_mm_ss_aa_z");
		String 				timeString			= simpleDateFormat.format(currentDate);
		
		String				fileName			= timeString + "___Test-Report.html";
		String				filePath			= userDir + "/Reports/" + yearFolder + "/" + monthFolder + "/" + dayFolder + "/" + fileName;
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setCSS(css);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportName);
		htmlReporter.config().setReportName(reportName);
		
		//	as an alternative to using htmlReporter.config() code lines, the extent-config.xml file could be loaded instead
//		htmlReporter.loadXMLConfig(userDir + "/Configuration/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Orgainzation", "com.github.GandhiTC");
		extent.setSystemInfo("Host", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Tejas Gandhi");
		extent.setSystemInfo("Build No", "TG00-000x");
		
		return extent;
	}
	
	
	/*
	public static String	screenshotPath;
	public static String	screenshotName;


	public static void captureScreenshot()
	{
		File	scrFile			= ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		Date	d				= new Date();
				screenshotName 	= d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

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
