package com.guru.pages;

import com.guru.base.GuruBankBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuruBankLogin extends GuruBankBase {

    @FindBy(name="uid")
    WebElement username;

    @FindBy(name="password")
    WebElement password;

    @FindBy(name="btnLogin")
    WebElement submitBtn;

    String alertTitle;

    String pageTitle;

    public GuruBankLogin() {
        PageFactory.initElements(driver, this);
    }

    public String login(String uname, String pwd) {
        username.sendKeys(uname);
        password.sendKeys(pwd);
        submitBtn.click();

        try {
            Alert alert = driver.switchTo().alert();
            pageTitle = alert.getText();
            alert.accept();

//            return alertTitle;
        } catch (Exception e) {
            // goes to here, meaning login is successful
            pageTitle = driver.getTitle();
            goesToHomePage();
        }

        return pageTitle;
    }

    public GuruBankHome goesToHomePage() {
        return new GuruBankHome();
    }
}
