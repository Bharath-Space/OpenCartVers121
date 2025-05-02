package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = ("//input[@placeholder='Search']"))
	WebElement txtproductName;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement clickonSearch;
	@FindBy(xpath="//div[@class='caption']//a[contains(text(),'iPhone')]")
	WebElement getProductText;

	public void searchProduct(String product_name) {
		txtproductName.sendKeys(product_name);
	}

	public void clickSearch() {
		clickonSearch.click();
	}
	public String getProductName() {
	    return getProductText.getText();
	}

}
