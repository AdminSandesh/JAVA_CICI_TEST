package com.SpurCumber.Pages;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;

public class DemoWinAppPage {
    private final WindowsDriver<?> winAppDriver;

    public DemoWinAppPage(WindowsDriver<?> _winAppdriver) {
        this.winAppDriver = _winAppdriver;
    }

    public void EnterNumber(String number) {
        switch (number) {
            case "1":
                winAppDriver.findElementByName("One").click();
                break;
            case "2":
                winAppDriver.findElementByName("Two").click();
                break;
            case "3":
                winAppDriver.findElementByName("Three").click();
                break;
            case "4":
                winAppDriver.findElementByName("Four").click();
                break;
            case "5":
                winAppDriver.findElementByAccessibilityId("num5Button").click();
                break;
            case "6":
                winAppDriver.findElementByName("Six").click();
                break;
            case "7":
                winAppDriver.findElementByName("Seven").click();
                break;
            case "8":
                winAppDriver.findElementByName("Eight").click();
                break;
            case "9":
                winAppDriver.findElementByName("Nine").click();
                break;
            case "0":
                winAppDriver.findElementByName("Zero").click();
                break;
        }
    }

    public void ClickOperator(String Operator) {
        switch (Operator) {
            case "+":
                winAppDriver.findElementByName("Plus").click();
                break;
            case "-":
                winAppDriver.findElementByName("Minus").click();
                break;
            case "/":
                winAppDriver.findElementByName("Divide by").click();
                break;
            case "*":
                winAppDriver.findElementByName("Multiply by").click();
                break;
        }
    }

    public String returnResult()
    {
        winAppDriver.findElementByName("Equals").click();
        WebElement CalculatorResult = winAppDriver.findElementByAccessibilityId("CalculatorResults");
        return CalculatorResult.getText().replace("Display is", "").trim();
    }
}
