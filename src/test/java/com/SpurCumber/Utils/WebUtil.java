package com.SpurCumber.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class WebUtil {
    static WebDriver driver;
    static String browser = null;
    static String browser_version = null;
    static String webExecutionMode = null;
    static String browser_executable_path = null;
    static String os = null;
    static String os_version = null;
    static String resolution = null;
    static String browserstack_user = null;
    static String browserstack_key = null;
    static String cloud_hub_URL = null;
    static String downloadFilepath = null;

    public static WebDriver WebInit() {
        try {
            browser = ConfigUtil.getPropValues("browser");
            webExecutionMode = ConfigUtil.getPropValues("WebExecutionMode");
            browser_executable_path = ConfigUtil.getPropValues("browser_executable_path");
            browser_version = ConfigUtil.getPropValues("browser_version");
            os = ConfigUtil.getPropValues("os");
            os_version = ConfigUtil.getPropValues("os_version");
            resolution = ConfigUtil.getPropValues("resolution");
            browserstack_user = ConfigUtil.getPropValues("browserstack_user");
            browserstack_key = ConfigUtil.getPropValues("browserstack_key");
            cloud_hub_URL = ConfigUtil.getPropValues("cloud_hub_URL");
            downloadFilepath = ConfigUtil.getPropValues("downloadFilepath");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (webExecutionMode.equals("Local")) {
            switch (browser) {
                case "Chrome":
                	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sandesh\\Downloads\\chromedriver-win64\\chromedriver.exe");
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("-no-sandbox");
//                    options.addArguments("--disable-dev-shm-usage");
//                    options.addArguments("--headless");
//                    WebDriverManager.chromedriver().setup();
//                    options.setBrowserVersion("119");
                    driver = new ChromeDriver();
                    break;
                case "Edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Please update config.properties with correct browsername");
                    break;
            }

        } else if (webExecutionMode.equals("Cloud_Web")) {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability("os", os);
            capability.setCapability("os_version", os_version);
            capability.setCapability("browser", browser);
            capability.setCapability("resolution", resolution);
            capability.setCapability("browser_version", browser_version);
            capability.setCapability("browserstack.user", browserstack_user);
            capability.setCapability("browserstack.key", browserstack_key);
            String URL = "https://" + browserstack_user + ":" + browserstack_key + cloud_hub_URL;
            try {
                driver = new RemoteWebDriver(new URL(URL), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void WebTearDown() {
        driver.quit();
    }

    public static byte[] takeWebAppScreenshotBase64() throws Exception {
        TakesScreenshot takesScreenshot =((TakesScreenshot)driver);
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
    public static String createFileName() throws IOException {
        long millisStart = Calendar.getInstance().getTimeInMillis();
        String fileName = millisStart + ".png";
        return fileName;
    }
}
