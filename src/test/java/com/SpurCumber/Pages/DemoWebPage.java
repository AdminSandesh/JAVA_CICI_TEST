package com.SpurCumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoWebPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public DemoWebPage(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;

    }

    public void EnterNumber(String number) {
        char[] digits = number.toCharArray();
        for (int i = 0; i <= number.length() - 1; i++) {
            if (digits[i] == '.')
                driver.findElement(By.xpath("//span[@onclick=\"r('" + digits[i] + "')\"]")).click();
            else
                driver.findElement(By.xpath("//span[@onclick='r(" + digits[i] + ")']")).click();
        }
    }

    public void ClickOperator(String Operator) {
        driver.findElement(By.xpath("//span[@onclick=\"r('" + Operator + "')\"]")).click();
    }

    public String GetResult() {
        WebElement result = driver.findElement(By.id("sciOutPut"));
        return (result.getText()).trim();
    }


    public String GetTitle() {
        return driver.getTitle();
    }

}
