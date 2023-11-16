package com.SpurCumber.Steps;

import com.SpurCumber.Pages.DemoWebPage;
import com.SpurCumber.Utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DemoWebSteps extends TestContext {

    private final DemoWebPage demoWebPage;

    public DemoWebSteps() {
        demoWebPage = new DemoWebPage(webDriver, wait);
    }

    @Given("I am on Calculator homepage")
    public void iAmOnCalculatorHomepage() {
        Assert.assertTrue((demoWebPage.GetTitle()).contains("Calculator.net: Free Online Calculators - Math, Fitness, Finance, Science"));
    }

    @When("I enter {string} in calculator")
    public void iEnterNumberInCalculator(String number) {
        demoWebPage.EnterNumber(number);
    }

    @And("I press {string}")
    public void iPressOperator(String op) {
        demoWebPage.ClickOperator(op);
    }

    @Then("I see the result is {string}")
    public void iSeeTheResultIsResult(String Expected_result) {
        Assert.assertEquals(demoWebPage.GetResult(), "Failing Intens");
    }
}
