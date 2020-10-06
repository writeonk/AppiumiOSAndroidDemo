/*
@author Kunal Soni
*/

package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import library.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestBase {

    public static Properties properties;
    public ExtentTest test;

    public static Date date = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static String dt = formatter.format(date);
    public static FileReader fileReader;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest reportLog;
    public static String reportDestination = "reports/report_" + dt + ".html";

    public static WebDriver webdriver = new WebDriver();
    public static IOSDriver driver;
    public static URL url;

    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeTest
    public static void setup() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.UDID, "0000XXXX-00XXX0D9XXXXXXXX");
        cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.Common.ios.dev");
        url = new URL("http://localhost:4723/wd/hub");
        driver = new IOSDriver(url, cap);
    }

    @BeforeSuite(alwaysRun = true)
    public void reportSetup() throws IOException {

        propertiesLoad();
        extentReport();
    }

    @AfterSuite
    public void reportTeardown() {
        extent.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED " + result.getName());
            test.log(Status.FAIL, "TEST CASE FAILED " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case SKIPPED " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case PASSED " + result.getName());
        }
    }

    public void extentReport() {

        htmlReporter = new ExtentHtmlReporter(reportDestination);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Environment", properties.getProperty("Environment"));
        extent.setSystemInfo("Device", properties.getProperty("Device"));
        extent.setSystemInfo("Software Version", properties.getProperty("SoftwareVersion"));
        extent.setSystemInfo("User Name", properties.getProperty("UserName"));
        extent.setSystemInfo("User Email", properties.getProperty("UserEmail"));

        htmlReporter.config().setDocumentTitle("iOs Test Suite");
        htmlReporter.config().setReportName("iOs Automation Testing Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public void propertiesLoad() throws IOException {

        try {
            fileReader = new FileReader("config/QA.properties");
            properties = new Properties();
            properties.load(fileReader);

        } catch (FileNotFoundException ex) {
            reportLog.info("*************************************************");
            reportLog.info("Property file you are looking for does not exist.");
            reportLog.info("*************************************************");
        }
    }
}
