package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
// Re usable componet of all the po claases
public class HomePage extends BasePage {

	// Page Object Class--->1 for Home Page
       
	// without creating this constructor again so we can't invoke the parent class constructor
//	  so again in this we creating construcotr
	// INheritance, Reusability cocncept
	
	// INImmediate Parent class variable,method,construtor invoke by uisng super
	// keyword
 // here expecting driver from the test case---> we all get which browser 
	public HomePage(WebDriver driver) { // So BAse class has pagefactory predefined clas which
//		                                     can do the work of driver.close();.find element
		super(driver);
// passing driver to parents class constructor (basepage)
//		base page will recieve the driver and intiate the driver
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkAccount;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;

//	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
//	WebElement lnkLogin;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	public void clickMyAccount() {
		lnkAccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();

	}

	public void clickLOgin() {
		lnkLogin.click();

	}

}
