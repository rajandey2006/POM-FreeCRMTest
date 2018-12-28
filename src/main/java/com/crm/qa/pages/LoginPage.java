package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// All the pages are child class of Base class
public class LoginPage extends TestBase {
// Page Factory - Object Repository (OR) -- every page should have OR

    @FindBy(name = "username")
    WebElement Username;

    @FindBy(name = "password")
    WebElement Password;

    @FindBy(xpath="//input[@type='submit']")
    WebElement LoginButton;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/a/img")
    WebElement CRMImage;

    // Initializing page objects/ elements -- like driver.findElement(by.xpath())
    public LoginPage() {
        PageFactory.initElements(driver, this); //how will you initialize page factory? All the PF elements in 'this' class will be initialized with driver. This      points to current class object. We can also use LoginPage.class instead of this --- PageFactory.initElements(WebDriver driver, page object);
    }

    // Actions:
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean validateCRMImage() {
        return CRMImage.isDisplayed();
    }

    public HomePage login(String un, String pw){
        Username.sendKeys(un);
        Password.sendKeys(pw);
        //LoginButton.click();
        // Once clicked on login button, it should land on the home page ... so the return type should be new HomePage(), hence changing the return type from void to HomePage
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", LoginButton);
        return new HomePage();
    }

}
