package com.sdev.definitions.navigation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class Meta_nav {

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        Configuration.timeout = 8000;
        Configuration.browserCapabilities = options;
        ///WebDriverRunner.setWebDriver(new ChromeDriver(options));
    }
    @Given("I'm on the {string} homepage")
    public void i_m_on_the_homepage(String string) {
        open(string);
    }
    @Given("I close the cookies banner")
    public void i_close_the_cookies_banner(){

        //using Selenide
        // shadowCss accepts only Css-selectors as args and doesn't have support for SelenideElement or Xpath as paramter
        String hostCss = "div[style*='position: fixed']";
        String targetCss = "button.js-accept-all-cookies";
        if(!$$(hostCss).isEmpty())
        {
            SelenideElement acceptAllCookiesButton = $(shadowCss(targetCss,hostCss));
            acceptAllCookiesButton.shouldBe(Condition.visible).click();
        }

        //using Selenium
		/*
		WebDriver driver = WebDriverRunner.getWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String hostPath = "//div[contains(@style, 'position: fixed') and contains(@style, 'height: 100%')]";
		if(!driver.findElements(By.xpath(hostPath)).isEmpty())
		{
			WebElement shadowHost = driver.findElement(By.xpath(hostPath));
			WebElement acceptAllCookiesButton = shadowHost.getShadowRoot().findElement(By.cssSelector("button.js-accept-all-cookies"));
			acceptAllCookiesButton.click();
		} */

    }
    @When("I click on a menu {string}")
    public void i_click_on_a_menu(String string) throws InterruptedException{
        Thread.sleep(3000);
		$(By.xpath("//a[text()=\'"+string+"\']")).shouldBe(Condition.visible).click();
    }

    @Then("I should be directed to the correct address {string}")
    public void i_should_be_directed_to_the_correct_address(String string) {
        String actualURL =  WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(string,actualURL);
        //closeWebDriver();
    }

}
