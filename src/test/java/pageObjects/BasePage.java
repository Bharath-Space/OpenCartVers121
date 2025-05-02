package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//This is the parent of all page OBject classes

// For Every page object classs constr is same;
public class BasePage {

	WebDriver driver;
	// int i;

	BasePage(WebDriver driver) {

		this.driver = driver;
		                                // this.i=i;
		                   
//	                                  PageFactory.initElements(driver, this);
		
//		                               / Important: initializes all @FindBy elements in the child class
		
		PageFactory.initElements(driver, this);
	}

}

//	int i;            // declares a box that holds integers
//	i = 1000;         // now the box has the value 1000
//
//	WebDriver driver;             // declares a reference that can hold WebDriver objects
//	driver = new ChromeDriver();  // now it holds the Chrome browser automation object

//--------------------------------------------------------------------------------

//@FindBy	Declares the locator in a readable way.
//PageFactory.initElements()	Reads all @FindBy fields and connects them using the driver (creates proxy objects).
//         proxy objects: txtEmailAddress,txtPassword

//BasePage	Parent class to centralize driver and initialization.
//LoginPage	Contains page-specific elements and actions (like login()).
//LoginTest	The test class that drives everything.
