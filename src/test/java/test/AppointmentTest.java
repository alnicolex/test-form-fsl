package test;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.FormDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.AppointmentPage;

import java.util.List;
import java.util.Map;

/**
 * Class pet appointment scheduling
 */
public class AppointmentTest {

    private WebDriver driver;
    private AppointmentPage pageRegister;
    private FormDto formDto;


    @Before
    /**
     *  Instance Webdriver
     */
    public void starTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        formDto = new FormDto();
        pageRegister = new AppointmentPage(driver);
    }


    @Given("The user accesses the home page")
    public void theUserIsInHomePage(){
        driver.get("http://localhost:3000/");
    }

    @When("The home page appears")
    public void homePageAvailable(){
        Assert.assertTrue(pageRegister.loadPage(), "Error home page not available");
    }

    @When("The user enters information into the form")
    public void enterInformation(DataTable dataTable) {
        List<Map<String, String>> form = dataTable.asMaps(String.class, String.class);

        formDto.setPetName(form.get(0).get("petName"));
        formDto.setOwnerName(form.get(0).get("ownerName"));
        formDto.setDate(form.get(0).get("date"));
        formDto.setTime(form.get(0).get("time"));
        formDto.setSymptoms(form.get(0).get("symptoms"));
        pageRegister.register(formDto);
    }

    @When("The user clicks on add appointment")
    public void submit() {
        Assert.assertTrue(pageRegister.submit(), "Error register");
    }

    @Then("The record is generated in the system")
    public void recordIsGenerated()  {
        Assert.assertTrue(pageRegister.checkScheduled(formDto), "Error register");
    }

    @Then("The user clicks on delete appointment")
    public void deleteAppointment(){
        Assert.assertTrue(pageRegister.deleteAppointment(), "Error delete appointment");
    }

    @Then("The system generates an alert")
    public void alertMessage(){
        Assert.assertTrue(pageRegister.checkMessage(), "Error fields required");
    }

    @After
    public void endTest(){
        driver.close();
    }


}
