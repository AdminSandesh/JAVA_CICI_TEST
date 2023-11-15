package com.SpurCumber.Steps;

import com.SpurCumber.Utils.*;
import io.cucumber.java.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseSteps extends TestContext {
    private String appURL = null;
    private Long PAGE_LOAD_TIMEOUT = null;
    private Long IMPLICIT_WAIT = null;
    private Long EXPLICIT_WAIT = null;
    private String REPORT_PATH = "test-output/ZAP_Report.html";

    @Before("@ui")
    public void InitializeWeb(Scenario scenario) throws InterruptedException {
        webDriver = WebUtil.WebInit();
        try {
            appURL = ConfigUtil.getPropValues("AppURL");
            PAGE_LOAD_TIMEOUT = Long.parseLong(ConfigUtil.getPropValues("PAGE_LOAD_TIMEOUT"));
            IMPLICIT_WAIT = Long.parseLong(ConfigUtil.getPropValues("IMPLICIT_WAIT"));
            EXPLICIT_WAIT = Long.parseLong(ConfigUtil.getPropValues("EXPLICIT_WAIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        webDriver.get(appURL);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
    }

    @After("@ui")
    public void TearDownWeb(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here later
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser");
        WebUtil.WebTearDown();
    }

    @AfterStep("@ui")
    public void AfterEveryStepWeb(Scenario scenario) throws Exception {
        try {
            if(ConfigUtil.getPropValues("SecurityScan").equals( "ON"))
                ZapUtil.Spider(webDriver.getCurrentUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        scenario.attach(WebUtil.takeWebAppScreenshotBase64(), "image/png", WebUtil.createFileName());
        //Allure.addAttachment("Any text",WebUtil.takeWebAppScreenshotBase64());
    }

    @AfterStep("@api")
    public void AfterEveryAPIStepAPI(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        try {
            if(ConfigUtil.getPropValues("SecurityScan").equals("ON"))
                ZapUtil.Spider(APIURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Before("@app")
//    public void InitializeMobileApp(Scenario scenario){
//        androidDriver = AppUtil.AppInit();
//
//    }
//
//    @After("@app")
//    public void TearDownMobileApp() {
//        AppUtil.AppTearDown();
//    }

//    @Before("@winApp")
//    public void InitializeWinApp(Scenario scenario){
//        winAppDriver = WinAppUtil.winAppInit();
//
//    }
//
//    @After("@winApp")
//    public void TearDownWinApp() {
//        WinAppUtil.winAppTearDown();
//    }
}
