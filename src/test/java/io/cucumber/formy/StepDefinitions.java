package io.cucumber.formy;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    WebDriver driver;
    WebElement autocompleteResult;
    Alert alert;

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
        autocomplete.sendKeys(adress);
    }

    @Then("Site shows autocomplete option")
    public void site_shows_autocomplete_option() throws InterruptedException {
        Thread.sleep(5000);
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

    @When("User clicks in open alert button")
    public void user_clicks_in_open_alert_button() {
        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();
    }

    @Then("Site shows a alert with message {string}")
    public void site_shows_a_alert_with_message(String msg) {
        alert = driver.switchTo().alert();
        assertEquals(msg, alert.getText());
    }

    @Then("User clicks on OK button")
    public void user_clicks_on_OK_button() {
        alert.accept();
        driver.quit();
    }

    @When("User clicks in open modal button")
    public void user_clicks_in_open_modal_button() {
        WebElement modalButton = driver.findElement(By.id("modal-button"));
        modalButton.click();
    }

    @Then("Site shows a modal with title {string}")
    public void site_shows_a_modal_with_title(String modalTitle) {
        WebElement modalButton = driver.findElement(By.xpath("//h5[@id='exampleModalLabel']"));
        assertEquals(modalTitle, modalButton.getAttribute("innerHTML"));
    }

    @Then("User clicks on close button")
    public void user_clicks_on_close_button() {
        WebElement closeButton = driver.findElement(By.id("close-button"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", closeButton);
        driver.quit();
    }

    @When("User drag and drop a image to the box")
    public void user_drag_and_drop_a_image_to_the_box() {
        WebElement image = driver.findElement(By.id("image"));
        WebElement box = driver.findElement(By.id("box"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(image,box).build().perform();
    }

    @Then("Site shows the message {string} in the box")
    public void site_shows_the_message_in_the_box(String droppedMsg) {
        Boolean checkDrop = driver.findElement(By.xpath("//p[contains(text(),'" + droppedMsg +"')]")).isDisplayed();
        assertEquals(true, checkDrop);
        driver.quit();
    }

    @When("User clicks on the three radio buttons")
    public void user_clicks_on_the_three_radio_buttons() {
        WebElement radioButton1 = driver.findElement(By.id("radio-button-1"));
        radioButton1.click();

        WebElement radioButton2 = driver.findElement(By.cssSelector("input[value='option2']"));
        radioButton2.click();

        WebElement radioButton3 = driver.findElement(By.xpath("/html/body/div/div[3]/input"));
        radioButton3.click();
    }

    @When("User chooses the date {string} and press enter")
    public void user_chooses_the_date_and_press_enter(String date) {
        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.RETURN);
    }

    @When("User clicks on dropdown button and chooses autocomplete option")
    public void userClicksOnDropdownButtonAndChoosesAutocompleteOption() {
        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
        dropDownMenu.click();

        WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
        autocompleteItem.click();
    }

    @When("User chooses to upload the {string} file")
    public void userChoosesToUploadTheFile(String fileName) {
        WebElement fileUploadField = driver.findElement(By.id("file-upload-field"));
        fileUploadField.sendKeys(fileName);
    }

    @When("User set first name as {string}")
    public void userSetFirstNameAs(String firstName) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
    }

    @And("User set last name as {string}")
    public void userSetLastNameAs(String lastName) {
        driver.findElement(By.id("last-name")).sendKeys(lastName);
    }

    @And("User set job title as {string}")
    public void userSetJobTitleAs(String jobTitle) {
        driver.findElement(By.id("job-title")).sendKeys(jobTitle);
    }

    @And("User set highest level of education as {string}")
    public void userSetHighestLevelOfEducationAs(String education) {

        if(education.equals("High School")){
            driver.findElement(By.id("radio-button-1")).click();
        }else if(education.equals("College")){
            driver.findElement(By.id("radio-button-2")).click();
        }else if(education.equals("Grad School")){
            driver.findElement(By.id("radio-button-3")).click();
        }
        else{
            throw new IllegalArgumentException("ERROR: " + education + "is not a education option");
        }
    }

    @And("User set sex as {string}")
    public void userSetSexAs(String sex) {

        if(sex.equals("Male")){
            driver.findElement(By.id("checkbox-1")).click();
        }else if(sex.equals("Female")){
            driver.findElement(By.id("checkbox-2")).click();
        }else if(sex.equals("Prefer not to say")){
            driver.findElement(By.id("checkbox-3")).click();
        }
        else{
            throw new IllegalArgumentException("ERROR: " + sex + "is not a sex option");
        }
    }

    @And("User set years of experience as {int}")
    public void userSetYearsOfExperienceAs(int experience) {

        if(experience>=0 && experience<=1 ){
            driver.findElement(By.cssSelector("option[value='1']")).click();
        }else if(experience>=2 && experience<=4 ){
            driver.findElement(By.cssSelector("option[value='2']")).click();
        }else if(experience>=5 && experience<=9){
            driver.findElement(By.cssSelector("option[value='3']")).click();
        }
        else if(experience >= 10){
            driver.findElement(By.cssSelector("option[value='4']")).click();
        }
        else{
            throw new IllegalArgumentException("ERROR: " + experience + "is not valid");
        }
    }

    @And("User set date as {string}")
    public void userSetDateAs(String date) throws InterruptedException {
        driver.findElement(By.id("datepicker")).sendKeys(date);
        driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);
    }

    @And("User clicks on submit button")
    public void userClicksOnSubmitButton() {
        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();
    }

    @Then("System redirects to page with {string} message")
    public void systemRedirectsToPageWithMessage(String msg) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement alert = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("alert"))));

        assertEquals(msg, alert.getText());

        driver.quit();
    }
}
