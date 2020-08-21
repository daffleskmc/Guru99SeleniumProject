package com.guru.util;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Guru99Util {

    public static long PAGE_LOAD_TIMEOUT = 30;

    public static long IMPLICIT_TIMEOUT = 40;

    public static Workbook book;
    public static Sheet sheet;
    public static JavascriptExecutor js;

    public static Object[][] getTestData(String filePath, String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//         System.out.println(sheet.getLastRowNum() + "--------" +
//         sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
//                 System.out.println(data[i][k]);
            }
        }
        return data;
    }

    public static void selectFromDropdown(WebDriver driver, By byTitle, String element) {
        List<WebElement> listTitle = driver.findElements(byTitle);

        Iterator<WebElement> it = listTitle.iterator();
        while(it.hasNext()) {
            WebElement el = it.next();
            if (el.getText().equalsIgnoreCase(element)) {
                el.click();
                break;
            }
        }
    }

    public static void scrollToBottomOfPage(WebDriver driver) {
        try {
            long Height = Long.parseLong(
                    ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight").toString());

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

                Thread.sleep(3000);

                long newHeight = Long.parseLong(
                        ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight").toString());

                if (newHeight == Height) {
                    break;
                }
                Height = newHeight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Scrolls to top of page
    public static void scrollToTopOfPage(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0);");

    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitTilPresent(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static By getDynamicXpath(String xpath,String data)
    {
        String rawXpath=xpath.replaceAll("%replaceable%", data);
        System.out.println(rawXpath);
        return By.xpath(rawXpath);
    }

}
