package testBase;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * BaseClass - Parent class for all test case classes.
 * It handles WebDriver initialization, setup, teardown, utility methods.
 */
public class BaseClass {

    public static WebDriver driver;       // WebDriver instance (shared across test classes)
    public Logger logger;                 // Logger for logging
    public Properties p;                  // Properties object to read config.properties

    /**
     * Setup method - executed before every test class.
     * Initializes WebDriver based on execution environment.
     */
    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})   // parameters are fetched from testng.xml
    public void setup(String os, String br) throws IOException {

        // Load config.properties file
        FileReader file = new FileReader(".//src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize Logger for this class
        logger = LogManager.getLogger(this.getClass());

        // Check if environment is remote (Selenium Grid) or local
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

            // Remote execution (Grid setup)
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set platform
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching OS found!");
                return;
            }

            // Set browser
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No matching browser found!");
                    return;
            }

            // Initialize RemoteWebDriver
            driver = new RemoteWebDriver(new URL("http://192.168.29.187:4444/wd/hub"), capabilities);

        } 
        
        if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

            // Local execution
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser name!");
                    return;
            }

        }

        // Common setup steps for both local & remote
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appUrl")); // Open application URL from properties
        driver.manage().window().maximize();
    }

    /**
     * Teardown method - executed after every test class.
     * Quits the browser session.
     */
    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        driver.quit();
    }

	public String randomString() {
		// which is there from commons library we added in pom not from java
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		// which is there from commons library we added in pom not from java
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		// which is there from commons library we added in pom not from java
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = (File) takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
