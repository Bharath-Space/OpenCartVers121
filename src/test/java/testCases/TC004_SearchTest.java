package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void searchProduct() {
		logger.info("*********TC004_SearchProduct******");
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
		sp.searchProduct(p.getProperty("Search"));
		sp.clickSearch();
		
		String actualProduct = sp.getProductName().trim();
		Assert.assertTrue(actualProduct.equalsIgnoreCase("iPhone"), "Product name doesn't match!");
//
//		String actualProduct = sp.getProductName();
//		Assert.assertEquals(actualProduct.toLowerCase(), "iphone", "Product name doesn't match!");
//	 
//		String actualProduct = sp.getProductName();
//		Assert.assertTrue(actualProduct.equalsIgnoreCase("iphone"), "Product name doesn't match!");

//		String actualproduct = sp.getProductName();
//		Assert.assertEquals(actualproduct, "iphone", "Product name doesn't match!");
		 logger.info("*********Search Product Test Passed*********");
//		@FindBy(xpath="//a[normalize-space()='iPhone']")

	}
}
