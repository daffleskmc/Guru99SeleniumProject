package com.guru.pages;

import com.guru.base.GuruBankBase;
import org.openqa.selenium.support.PageFactory;

public class GuruBankHome extends GuruBankBase {

    public GuruBankHome() {
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }
}
