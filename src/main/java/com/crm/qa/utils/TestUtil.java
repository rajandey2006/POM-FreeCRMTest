package com.crm.qa.utils;

import com.crm.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long  IMPLICIT_WAIT = 20;

    public void switchToFrame() {
        driver.switchTo().frame("mainpanel");
    }

    public static String TESTDATA_SHEET_PATH = "C:\\Users\\rajan.dey\\IdeaProjects\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
    static Workbook wbook;
    static Sheet sheet;
    static JavascriptExecutor js;


     // Create a method with sheetname parameter
    // FileInputStream fis = new FileInputStream(excelfilepath); --> now we have the excel testdata file
    // Workbook wbook = WorkbookFactory.create(fis) --> create workbook from fis
    // Worksheet sheet = wbook.getSheet(sheetname)

    public static Object[][] getTestData(String sheetName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wbook = WorkbookFactory.create(fis);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wbook.getSheet(sheetName);


        // Now the sheet is ready without data ... we now need to create data (2 dimensional) which is type object
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // ex: data[rownumber][cellnumber] -- > data[3][5]
        // System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

    public static void runTimeInfo(String messageType, String message) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        // Check for jQuery on the page, add it if need be
        js.executeScript("if (!window.jQuery) {"
                + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
                + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
                + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
        Thread.sleep(5000);

        // Use jQuery to add jquery-growl to the page
        js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

        // Use jQuery to add jquery-growl styles to the page
        js.executeScript("$('head').append('<link rel=\"stylesheet\" "
                + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
        Thread.sleep(5000);

        // jquery-growl w/ no frills
        js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
        if (messageType.equals("error")) {
            js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
        }else if(messageType.equals("info")){
            js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
        }else if(messageType.equals("warning")){
            js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
        }else
            System.out.println("no error message");
        // jquery-growl w/ colorized output
//		js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
        Thread.sleep(5000);
    }






}
