package com.SpurCumber.Utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestContext {
    public static WebDriver webDriver;
    public static WebDriverWait wait;
    public static String APIURL;
    public static AndroidDriver<AndroidElement> androidDriver;
    public static WindowsDriver winAppDriver;
}
