package com.SpurCumber.Utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import static java.lang.Thread.sleep;


@CucumberOptions(features = {"src/test/java/com/SpurCumber/Features"},
        glue = "com.SpurCumber.Steps",
        plugin = {"json:test-output/Cucumber.json", "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })
public class TestRunner extends AbstractTestNGCucumberTests {
    private final String REPORT_PATH = "test-output/ZAP_Report.html";
    private String SecurityScanMode = null;

    @DataProvider
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void BeforeSuite() {
        try {
            SecurityScanMode = ConfigUtil.getPropValues("SecurityScan");
            if (SecurityScanMode.equals("ON"))
                ZapUtil.StartZAP();
            try {
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterSuite
    public void AfterSuite() {
        try {
            SecurityScanMode = ConfigUtil.getPropValues("SecurityScan");
            if (SecurityScanMode.equals("ON")) {
                if (ConfigUtil.getPropValues("FrameworkMode").equals("Web"))
                    ZapUtil.ActiveScan(ConfigUtil.getPropValues("AppURL"));
                else if (ConfigUtil.getPropValues("FrameworkMode").equals("API"))
                    ZapUtil.ActiveScan(ConfigUtil.getPropValues("baseURL"));
                ZapUtil.ShutdownZAP();
                ZapUtil.WriteHtmlReport(REPORT_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



