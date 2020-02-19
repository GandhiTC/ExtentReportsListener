ExtentReportsListener is a wrapper for using ExtentReports (v4) with TestNG.




------------------------------------------------------------------------------------------------------------------------------------------------------------
TestCase1.java
------------------------------------------------------------------------------------------------------------------------------------------------------------
Can be run as a TestNG Test
	- Because it has ExtentReports code within the file itself, it will generate a report file (./reports/extent.html)
	- In this example, the report file name is static, therefore the report file will be overwritten each time a test is run.

Can be run as a TestNG Suite
	- Edit testng.xml so that it points to TestCase1, then run the xml file as a TestNG Suite
	- The TestNG listeners for ExtentReport will take precedence over the ExtentReports code within the TestCase1.java file
	- A new file, with a custom name based on the date and time, will be created each time (in directory:  ./ExtentReports/)



------------------------------------------------------------------------------------------------------------------------------------------------------------
TestCase2.java
------------------------------------------------------------------------------------------------------------------------------------------------------------
Listener for ExtentReports is added to the testng.xml file.
	- There are other ways to include listeners such as using the command line or specifically setting listeners in pom.xml.
	
Can be run as a TestNG Test
	- NOTE:  No report file will be created since there is no ExtentReports code in the file itself.

Can be run as a TestNG Suite
	- Edit testng.xml so that it points to TestCase2, then run the xml file as a TestNG Suite
	- A new file, with a custom name based on the date and time, will be created each time (in directory:  ./ExtentReports/)



------------------------------------------------------------------------------------------------------------------------------------------------------------
It is not feasible to add code for ExtentReports to each test file, using listeners instead prevents unnecessary code repetition and is much cleaner.
------------------------------------------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------------------------------------------
ExtentListener.java
------------------------------------------------------------------------------------------------------------------------------------------------------------
Code that customizes how ExtentReports interprets TestNG results.

Includes code to make sure tests that are automatically skipped by TestNG (due to failed/missing methods listed in the "dependsOnMethods" @Test attribute)
are included in the ExtentReports results.  By default, they are not included, see the report file for TestCase1.

In order to make the results of failed tests cleaner and easier to read, code to allow expanding/collapsing of the stack trace text was added.



------------------------------------------------------------------------------------------------------------------------------------------------------------
ExtentManager.java
------------------------------------------------------------------------------------------------------------------------------------------------------------
Settings for ExtentReport's HTMLReporter are set in this file, alternatively, the extent-config.xml file can be used instead.

Any additional code/method specifically for use with ExtentReports, can be added in this file.



