package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

//✅ It implements ITestListener — meaning it will listen to TestNG test events like onStart, onFinish, onTestSuccess, etc.
//✅ You declared three important variables:
//
//sparkReporter → used to create the HTML report file.
//
//extent → the main controller that manages the whole report.
//
//test → represents each individual test inside the report.
//
//repName → to store the report file name (with date/time inside it).

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {

		// This creates a formatter that knows how to arrange date and time.
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt =new Date();         //(Sat Apr 26 21:20:35 IST 2025)java code	//creates a new Date object that captures the current date and time 
//		String currentdatetimestamp=df.format(dt); // we need above format so we calling and store

//		In single Step below

		String timeStamp = (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")).format(new Date());

		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(new ExtentObserver[] { this.sparkReporter });
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		this.extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		this.extent.setSystemInfo("Browser", browser);
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			this.extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = (new BaseClass()).captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException var3) {
			var3.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + this.repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException var5) {
			var5.printStackTrace();
		}
		
		
//This is to Send emails indvidual or groups
//		try { URL url = new 
//				URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName); 
//				// Create the email message 
//				ImageHtmlEmail email= new ImageHtmlEmail(); 
//				email.setDataSourceResolver(new DataSourceUrlResolver(url)); 
//				email.setHostName("smtp.googlemail.com"); 
//				email.setSmtpPort(465); 
//				email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
//				email.setSSLOnConnect(true); 
//				email.setFrom("bharathdata@gmail.com"); //Sender 
//				email.setSubject("Test Results"); 
//				email.setMsg("Please find Attached Report...."); 
//				email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
//				email.attach(url, "extent report", "please check report..."); 
//				email.send(); // send the email 
//				} 
//				 catch(Exception e) { e.printStackTrace();
//				}

	}
}
