package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_Account_registration() {

		logger.info("******Starting TC001_AccountRegistrationTest.. ********");// log4j2
		try {
			// Constructor invoke at run time so driver pass
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();

			logger.info("******We clicked on mY account link.. ********");
			hp.clickRegister();

			logger.info("******We clicked on mY Register LInk... ********");

			AccountRegistrationPage rp = new AccountRegistrationPage(driver);

			logger.info("******Providing Customer Details...********");

			rp.setFirstName(randomString().toUpperCase());

			rp.setLastName(randomString().toUpperCase());
			// Evry time we cant send the same email//Email alreay Registred pop up
            //So we prepare random email

			// Static Random
			rp.setEmail(randomString() + "@gmail.com");
			rp.setTelephone(randomNumber());

			// String Pswa AlphaNumeric

			// Beacuse we can't pass same metho bcoz random genrate another
			String password = randomAlphaNumeric();
			rp.setPassword(password);
			rp.setConfirmPassword(password);

			rp.setPrivacyPolicy();
			rp.clickButton();

			logger.info("******Validating Expected Message....********");
			String confmsg = rp.getConfirmationMsg();
			
			
			
			
				if(confmsg.equals("Your Account Has Been Created!"))
						{
					Assert.assertTrue(true);
						}
				else {
					logger.error("******Test FAiled********");
					logger.debug("Debug LOgs");
					Assert.assertTrue(false);
				}
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("******Finished..TC001_AccountRegistrationTest ********");


	}
}
