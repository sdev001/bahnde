package com.sdev.definitions.pages;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Home_page {

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
    }
    @Given("I started a new browser session")
    public void i_started_a_new_browser_session() {
        open("about:blank");
    }
    @When("I open the DB Url {string}")
    public void i_open_the_db_url(String string) {
        open(string);
    }
    @Then("I should be on the DB-homepage {string}")
    public void i_should_be_on_the_db_homepage(String string) {
        String actualUrl =  WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(actualUrl.contains(string));
    }
    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        assertEquals(expectedTitle,title());
    }
}
