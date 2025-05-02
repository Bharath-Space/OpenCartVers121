package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage extends BasePage{

	public AddToCartPage(WebDriver driver) {
		super(driver);
		
	}
	//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//button[1]
	//@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//button[1]")
	
	@FindBy(xpath = "//button[.//span[normalize-space()='Add to Cart']]")
	WebElement  AddtoCart;
	
//
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successMsg;

	 // Clicks the Add to Cart button
    public void Productadding() {
        AddtoCart.click();
    }

    // Waits for and gets the success message text
    public String getSuccessMessage() {
        // 1️⃣ Create a "wait" object that checks for up to 10 seconds ⏱️
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 2️⃣ Wait until the success message becomes visible on the screen 👀
        wait.until(ExpectedConditions.visibilityOf(successMsg));
        
        // 3️⃣ Get the text from the message, remove the '×' symbol, and trim spaces 🧹
        return successMsg.getText().replace("×", "").trim(); 
    }
    
    
    
    
    
    
    

    // ✅ Method to be used in test for verifying cart addition
    public boolean isProductAddedToCart() {
        try {
            // 1️⃣ Get the cleaned-up success message text 📝
            String msg = getSuccessMessage();
            
            // 2️⃣ Print the message to the console for debugging 📢
            System.out.println("Success Message: " + msg);
            
            // 3️⃣ Check if the message says "Success: You have added..." ✅
            return msg.contains("Success: You have added");
        } catch (Exception e) {
            // 4️⃣ If something breaks (e.g., message never appears), catch the error ❌
            System.out.println("Exception in verifying cart: " + e.getMessage());
            return false;
        }
    }
}
	
	
	
