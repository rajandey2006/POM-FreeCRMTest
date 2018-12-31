package com.crm.qa.testcases;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactsPageTest extends TestBase{

    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;

    String sheetName = "contacts";


    public ContactsPageTest(){
        super();

    }

    @BeforeMethod
    public void setUp() throws InterruptedException {

        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        //TestUtil.runTimeInfo("error", "login successful");
        testUtil.switchToFrame();
        contactsPage = homePage.clickOnContactsLink();
    }

    @Test(priority=1)
    public void verifyContactsPageLabel(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
    }

    @Test(priority=2)
    public void selectSingleContactsTest(){
        contactsPage.selectContactsByName("Rajan Dey");
    }

    @Test(priority=3)
    public void selectMultipleContactsTest(){
        contactsPage.selectContactsByName("Rajan Dey");
        contactsPage.selectContactsByName("Abha Dey");

    }

    //
    //@DataProvider
    //public Object[][] getCRMTestData(){
       // Object data[][] = TestUtil.getTestData(sheetName);
        //return data;
    //}

@DataProvider
public Object[][] getCRMTestData(){

         Object[][] data= TestUtil.getTestData(sheetName);
         return data;
}

// Instead of hard coding data, in data driven approach, we should use data from excel file ... to do so, we need to define DataProvide and use it ...
    // lets define @DataProvider
    @Test(priority=4,dataProvider ="getCRMTestData")
    public void validateCreateNewContact(String Title, String FirstName, String LastName, String Company){ // parameters should exactly match that of test data sheet
        homePage.clickOnNewContactLink();
        //contactsPage.createNewContact("Mr.", "Ridhima", "Dey", "Wipro");
        contactsPage.createNewContact(Title,FirstName, LastName, Company );
    }

@Test(priority = 5)

    public void contactscreencapture(){
    try {
        testUtil.takeScreenshotAtEndOfTest();
    } catch (IOException e) {
        e.printStackTrace();
    }
  return;
   }




    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}