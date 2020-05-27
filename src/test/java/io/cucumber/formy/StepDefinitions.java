package io.cucumber.formy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

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

    @When("User informs the address {string}")
    public void user_informs_the_address(String adress)  {
        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("1555 Park Blvd, Palo Alto, CA");
    }

    @Then("Site shows autocomplete option")
    public void site_shows_autocomplete_option() throws InterruptedException {
        Thread.sleep(1000);
        autocompleteResult = driver.findElement(By.className("pac-item"));
    }

    @Then("user click in autocomplete option")
    public void user_click_in_autocomplete_option() {
        autocompleteResult.click();
    }

    @When("User informs it's name {string} and date {string}")
    public void user_informs_it_s_name_and_date(String name, String date){
        WebElement nameField = driver.findElement(By.id("name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(nameField);
        nameField.sendKeys(name);

        WebElement dateField = driver.findElement(By.id("date"));
        dateField.sendKeys(date);
    }

    @Then("the Zip code field shows value {string}")
    public void the_Zip_code_field_shows_value(String expectedZipcode) throws InterruptedException {
        Thread.sleep(1000);
        WebElement zipcodeField = driver.findElement(By.id("postal_code"));
        assertEquals(expectedZipcode, zipcodeField.getAttribute("value"));
        driver.quit();
    }

    @When("User clicks in open new tab button")
    public void user_clicks_in_open_new_tab_button()  {
        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();
    }

    @Then("New tab is created with the home page of Formy web site that has the message {string}")
    public void new_tab_is_created_with_the_home_page_of_Formy_web_site_that_has_the_message(String msg)  {

        String originalHandle = driver.getWindowHandle();

        for(String handle: driver.getWindowHandles()) {
            if(!originalHandle.equals(handle)){
                driver.switchTo().window(handle);
                WebElement titlePage = driver.findElement(By.xpath("//h1[@class='display-3']"));
                assertEquals(msg, titlePage.getAttribute("innerHTML"));
            }
        }

        driver.quit();
    }
}
