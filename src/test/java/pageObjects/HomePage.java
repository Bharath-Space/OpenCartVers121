package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// Page Object Class--->1 for Home Page

	// without creating this constr we can't invoke the parent class constructor
	// INheritance, Reusability
	// INImmediate Parent class variable,method,construtor invoke by uisng super
	// keyword

	public HomePage(WebDriver driver) { // So BAse class has pagefactory predefined clas which
//		                                     can do the work of driver.close();.find element
		super(driver);

	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkAccount;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;

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
