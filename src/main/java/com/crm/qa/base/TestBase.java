package com.crm.qa.base;

import com.crm.qa.utils.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static Properties prop;
    public static WebDriver driver;

    // Constructor of TestBase class
    // prop.load(ip) -- Assigning confirm.properties to prop -- instantiating Properties to read confirm.properties file for all the data in it

    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
                    + "/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


// Setting chromedriver
    public static void initialization(){
    String browsername = prop.getProperty("browser");

    if (browsername.equals("chrome")) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\NewtonDeploy\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver(); }
        else if (browsername.equals("firefox")) {
        System.setProperty("webdriver.gecko.driver", "C:\\Test Automation\\geckodriver.exe");
        driver = new FirefoxDriver();
    }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); //20 sec was hard coded, hence we replaced it with a variable from TestUtil
    driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS); //10 sec was hard coded, hence we replaced it with a variable from TestUtil

    driver.get(prop.getProperty("url"));

}
}
