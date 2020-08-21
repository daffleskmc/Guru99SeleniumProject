package com.guru.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GuruBankBase {

    public static WebDriver driver;

    public static Properties props;

    public GuruBankBase() {
        props = new Properties();

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/guru/config/guruConfig.properties");
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browser = props.getProperty("browser");

        if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(props.getProperty("url"));
    }


}
