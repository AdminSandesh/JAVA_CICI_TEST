package com.SpurCumber.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.concurrent.TimeUnit;

public class DemoAppPage {

    private final AndroidDriver<AndroidElement> appium_driver;
    // private WebDriverWait wait;


    public DemoAppPage(AndroidDriver<AndroidElement> _driver) {
        this.appium_driver = _driver;


    }

    public void ClickonLogin() {
        System.out.println("login");
        MobileElement username = appium_driver.findElementByXPath("//*[contains(@text,'LOGIN')]");
        username.click();

    }

    public void EnterUserName(String UserName) {
        appium_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement username = appium_driver.findElementByAccessibilityId("Username");
        username.click();
        username.sendKeys(UserName);


    }

    public void EnterPassword(String Password) {
        MobileElement Enterpassword = appium_driver.findElementByAccessibilityId("Password");
        Enterpassword.sendKeys(Password);

    }

    public void Clickon_LogIn() {
        MobileElement click_loginbutton = appium_driver.findElementByAccessibilityId("Log In");
        click_loginbutton.click();

        MobileElement els2 = appium_driver.findElementByXPath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]");
        els2.click();
        MobileElement el4 = appium_driver.findElementByAccessibilityId("Skip");
        el4.click();
        MobileElement el5 = appium_driver.findElementByAccessibilityId("Deny");
        el5.click();


    }

    public boolean VerfiyFacbookLoginPage() {
        MobileElement Fbpagetext = appium_driver.findElementByAccessibilityId("Make a post on Facebook");
        boolean Ele_isDisplayed = Fbpagetext.isDisplayed();
        return Ele_isDisplayed;

    }
}
