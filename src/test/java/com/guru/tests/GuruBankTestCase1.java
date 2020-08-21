package com.guru.tests;

import com.guru.base.GuruBankBase;
import com.guru.pages.GuruBankHome;
import com.guru.pages.GuruBankLogin;
import com.guru.util.Guru99Util;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GuruBankTestCase1 extends GuruBankBase {

    GuruBankLogin loginPage;

    GuruBankHome homePage;

    String filePath = "C:\\Users\\indeztruk\\eclipse-workspace\\Gur99SeleniumProject\\src\\main\\java\\com\\guru\\data\\Guru99TestData.xlsx";

    String sheetName = "Sheet1";

    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new GuruBankLogin();
    }

    @DataProvider
    public Object[][] getLoginData() {
        Object data[][] = Guru99Util.getTestData(filePath, sheetName);
        return data;
    }

//    @Test
//    public void testCase1() {
//        homePage = loginPage.login(props.getProperty("username"), props.getProperty("password"));
//
//        String actualTitle = homePage.getHomePageTitle();
//        System.out.println(actualTitle);
//
//        Assert.assertEquals(actualTitle, "Guru99 Bank Manager HomePage");
//    }

    @Test(dataProvider = "getLoginData")
    public void testCase_using_poi(String username, String password) {
        String pageTitle = loginPage.login(username, password);

//        String actualTitle = homePage.getHomePageTitle();
        System.out.println(pageTitle);

        if (pageTitle.contains("HomePage")) {
            Assert.assertEquals(pageTitle, "Guru99 Bank Manager HomePage");
        } else {
            Assert.assertEquals(pageTitle, "User or Password is not valid");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
