package com.crm.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;
public class ContactsPage extends TestBase {

    @FindBy(xpath = "//td[contains(text(),'Contacts')]")
    WebElement contactsLabel;

    @FindBy(id="first_name")
    WebElement firstName;

    @FindBy(id="surname")
    WebElement lastName;

    @FindBy(name="client_lookup")
    WebElement company;

    @FindBy(xpath = "//input[@type='submit' and @value='Save']")
    WebElement saveBtn;



    // Initializing the Page Objects:
    public ContactsPage() {
        PageFactory.initElements(driver, this);
    }


    public boolean verifyContactsLabel(){
        return contactsLabel.isDisplayed();
    }


    public void selectContactsByName(String name){
        //driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
               // + "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
        driver.findElement(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr[4]/td[2]/a")).click();
    }


    public void createNewContact(String title, String ftName, String ltName, String comp){
        Select select = new Select(driver.findElement(By.name("title")));
        select.selectByVisibleText(title); // Select title from the drop down list - Mr. Dr. Miss Ms. Mrs. etc
        firstName.sendKeys(ftName); // first time
        lastName.sendKeys(ltName);  // last name
        company.sendKeys(comp);     // company
        saveBtn.click();

    }




}
