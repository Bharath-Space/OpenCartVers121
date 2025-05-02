package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
@Test(groups={"Sanity","Master"})
	public void verify_login() {

			logger.info("*********verify_login Started");
			try {
			// In test cases we should not specify any hard code data anywher
//		should keep in poc or excels

			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLOgin();
			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("Password"));
			lp.clickLogin();

			// My Account page
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetPage = mac.isMyAccountPageExists();

//		Assert.assertEquals(targetPage,true,"Login failed");

			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*******verify_login Successful");
	}

}
