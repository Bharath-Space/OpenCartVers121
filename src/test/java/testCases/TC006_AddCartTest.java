package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AddToCartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC006_AddCartTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void verifyAddCart() {
	
			logger.info("*********Product AddingStarted");

			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLOgin();
			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("Password"));
			lp.clickLogin();

			// SearchPage
			SearchPage sp = new SearchPage(driver);
			sp.searchProduct("iPhone");
			sp.clickSearch();

			// addtocart

			AddToCartPage cartPage = new AddToCartPage(driver);
			cartPage.Productadding();

			Assert.assertTrue(cartPage.isProductAddedToCart(), "Product not added to cart");

}
}	