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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;       // WebDriver instance (shared across test classes)
    public Logger logger;                 // Logger for logging
    public Properties p;                  // Properties object to read config.properties

    // Setup method - executed before every test class
    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        // Load config.properties
        FileReader file = new FileReader(".//src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());

        // Remote or local execution
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // OS setup
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching OS found!");
                return;
            }

            // Browser setup
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No matching browser found!");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://192.168.29.187:4444/wd/hub"), capabilities);

        } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

            // Local driver setup
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

        // Common setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();
    }

    // Teardown after test class execution
    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        driver.quit();
    }

    // Generates a random alphabetic string
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // Generates a random numeric string
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    // Generates a random alphanumeric string
    public String randomAlphaNumeric() {
        String letters = RandomStringUtils.randomAlphabetic(3);
        String numbers = RandomStringUtils.randomNumeric(3);
        return letters + "@" + numbers;
    }

    // Takes a screenshot and saves with timestamp
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File target = new File(targetPath);
        source.renameTo(target);
        return targetPath;
    }

    // ---------- Explicit Wait Utility Methods ----------

    // Waits until the element is visible
    public void waitForElementVisible(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Waits until the element is clickable
    public void waitForElementClickable(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Waits until the given text is present in the element
    public void waitForTextToBePresent(WebElement element, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // Waits until URL contains a specific value
    public void waitForUrlContains(String partialUrl, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }
}
