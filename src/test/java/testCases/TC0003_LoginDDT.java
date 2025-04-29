package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Data valid------ login sucess--testpass -logout
//Data valid -----login failed--test fail
//Data invalid-----login suceess--test fail--logout
//Data in valid-	--login faile -- test pass

public class TC0003_LoginDDT extends BaseClass {
	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class,groups="Datadriven")
	public void verify_loginDDt(String email, String pwd, String exp) {
		

				logger.info("*********TC003_verify_login Started");
			
					// In test cases we should not specify any hard code data anywher
//				should keep in poc or excels
try {
					// Home page
					HomePage hp = new HomePage(driver);
					hp.clickMyAccount();
					hp.clickLOgin();
					// Login page
					LoginPage lp = new LoginPage(driver);
					lp.setEmail(email);
					lp.setPassword(pwd);
					lp.clickLogin();

					// My Account page
					MyAccountPage mac = new MyAccountPage(driver);
					boolean targetPage = mac.isMyAccountPageExists();

//				Assert.assertEquals(targetPage,true,"Login failed");
					
					//Data valid------ login sucess--testpass -logout
					//Data valid -----login failed--test fail
					//Data invalid-----login suceess--test fail--logout
					//Data in valid-	--login faile -- test pass
		
					if(exp.equalsIgnoreCase("Valid"))
							{
						if(targetPage==true)
						{
							mac.clickLogout();
							Assert.assertTrue(true);
							
						}
						else {
							Assert.assertTrue(false);
						}
					}
					if(exp.equalsIgnoreCase("Invalid"))
					{
						if(targetPage==true)
						{ 
								mac.clickLogout();
							Assert.assertTrue(false);
							
						}
						else {
							Assert.assertTrue(true);
						}
					}
					
					}
					catch(Exception e) {
						Assert.fail();
					}
					logger.info("*********TC003_verify_login Finished****");
					
					

	}		
}

	
