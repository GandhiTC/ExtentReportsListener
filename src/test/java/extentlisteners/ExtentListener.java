package extentlisteners;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ExtentListener implements ITestListener
{
	private static ExtentReports			extent		= ExtentManager.createInstance();
	public  static ThreadLocal<ExtentTest>	testReport	= new ThreadLocal<ExtentTest>();
	
	private static String					lastSkipped	= "";
	private		   String					logText		= "";
	private		   String					methodName	= "";


	@Override
	public void onTestStart(ITestResult result)
	{
					methodName 	= result.getMethod().getMethodName() + "()";
		ExtentTest	test		= extent.createTest(result.getTestClass().getName() + "." + methodName);
		
		testReport.set(test);
	}


	@Override
	public void onTestSuccess(ITestResult result)
	{
				logText		= "<b>TEST CASE PASSED : " + methodName + "</b>";
		Markup	m			= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		
		testReport.get().pass(m);
	}


	@Override
	public void onTestFailure(ITestResult result)
	{
				logText		= "<b>TEST CASE FAILED : " + methodName + "</b>";
		Markup	m			= MarkupHelper.createLabel(logText, ExtentColor.RED);
		
		testReport.get().log(Status.FAIL, m);
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		
		testReport.get().fail("<details><summary>Exception Occured : Click To View</summary>"
						+ "<p><span class=\"tabbed\"></span>" + exceptionMessage.replaceAll(", ", "<br><span class=\"tabbed\"></span>")
						+ "</p></details>");
		
		/*
		try
		{
			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		}
		catch(IOException e)
		{
		}
		*/
	}


	@Override
	public void onTestSkipped(ITestResult result)
	{
//				className	= result.getInstanceName();
				methodName 	= result.getMethod().getMethodName() + "()";
				logText		= "<b>TEST CASE SKIPPED : " + methodName + "</b>";
		Markup	m			= MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		
		if(result.getThrowable().getClass().getTypeName().equalsIgnoreCase("org.testng.SkipException"))
		{
			testReport.get().skip(m);
			return;
		}
		else if(result.getThrowable().getClass().getTypeName().equalsIgnoreCase("java.lang.Throwable"))
		{
			String trimmedMethodName = methodName.substring(0, methodName.length() - 2);
			
			if(!lastSkipped.equalsIgnoreCase(trimmedMethodName))
			{
				lastSkipped = result.getMethod().getMethodName();
				onTestStart(result);
				onTestSkipped(result);
			}
			else
			{
				testReport.get().log(Status.SKIP, m);
				
				String requiredMethods = "";
				
				if(result.getMethod().getMethodsDependedUpon() != null)
				{
					String[] dependencies = result.getMethod().getMethodsDependedUpon();
				
					if(dependencies.length > 0)
					{
						List<String> passedMethods = new ArrayList<String>();
						
						for(ITestResult passedResult : result.getTestContext().getPassedTests().getAllResults())
						{
							passedMethods.add(passedResult.getMethod().getMethodName());
						}
						
						for(String currMethod : dependencies)
						{
							if(!passedMethods.contains(currMethod))
							{
								requiredMethods += currMethod + "(), ";
							}
						}
						
						if(!requiredMethods.equalsIgnoreCase(""))
						{
							requiredMethods = requiredMethods.substring(0, requiredMethods.length() - 2);
							testReport.get().log(Status.SKIP, "Method: " + methodName + " depends on the following failed or skipped methods: " + requiredMethods);
							return;
						}
						else
						{
							testReport.get().log(Status.SKIP, Arrays.toString(result.getThrowable().getStackTrace()));
							return;
						}
					}
				}
			}
		}
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
	}


	@Override
	public void onStart(ITestContext context)
	{
	}


	@Override
	public void onFinish(ITestContext context)
	{
		if(extent != null)
		{
			extent.flush();
		}
	}
}