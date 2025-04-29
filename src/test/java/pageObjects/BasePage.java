package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//This is the parent of all page OBject classes


// foe Every poc constr is same;
public class BasePage {

	WebDriver driver;              
                                                //	int i;

	BasePage(WebDriver driver) {

		this.driver = driver;
                                                          //	this.i=i; 
    PageFactory.initElements(driver, this);
	}

}
