package io.cucumber.formy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    WebDriver driver;
    WebElement autocompleteResult;

    @Given("User access {string} page from Formy web site")
    public void user_access_page_from_Formy_web_site(String pageName) {
        System.setProperty("webdriver.chrome.driver", "/home/karla/Projects/dependencies/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/"+pageName);
    }

    @When("User informs it's name {string} and click on button")
    public void user_informs_and_click_on_button(String name) {
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.click();
        nameField.sendKeys(name);
        WebElement button = driver.findElement(By.id("button"));
        button.click();
    }

    @Then("Nothing happens")
    public void nothing_happens() {
        driver.quit();
    }






}
