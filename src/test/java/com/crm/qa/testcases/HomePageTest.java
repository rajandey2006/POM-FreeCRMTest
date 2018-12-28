package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    ContactsPage contactsPage;
    TestUtil testUtil;
    LoginPage loginPage;
    HomePage homePage;


    public HomePageTest(){
        super();
    }
    //test cases should be separated -- independent with each other
    //before each test case -- launch the browser and login
    //@test -- execute test case
    //after each test case -- close the browser

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }


    @Test (priority = 1)
    public void verifyHomePageTitleTest(){
       String hometitle= homePage.verifyHomePageTitle();
        Assert.assertEquals(hometitle,"CRMPRO", "Home page title does not match");
    }

   @Test (priority = 2)
    public void verifyUserNameTest() {
       testUtil.switchToFrame();
       Assert.assertTrue(homePage.verifyCorrectUserName());
    }

    @Test (priority = 3)
    public void verifyContactsLinkTest(){
            testUtil.switchToFrame();
            contactsPage = homePage.clickOnContactsLink();
    }





    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
