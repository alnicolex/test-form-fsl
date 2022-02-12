package pages;

import dto.FormDto;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.GeneralUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class with the elements and functionalities of the home page
 */
public class AppointmentPage {


    // Register
    public By inPetName = By.xpath("//input[@data-testid=\"pet\"]");
    public By inOwnerName = By.xpath("//input[@data-testid=\"owner\"]");
    public By inDate = By.xpath("//input[@data-testid=\"date\"]");
    public By inTime = By.xpath("//input[@data-testid=\"time\"]");
    public By inSymptoms = By.xpath("//textarea[@data-testid=\"symptoms\"]");
    public By btnSubmit = By.xpath("//button[@data-testid=\"btn-submit\"]");

    // Consult
    public String startTextField = "//div[@data-testid=\"appointment\"]/p/span[contains(text(), '";
    public String endTextField = "')]";
    public By btnDelete = By.xpath("//button[@data-testid=\"btn-delete\"]");

    // Alert
    public By alertError = By.xpath("//p[@data-testid=\"alert\"]");

    private WebDriver driver;
    private GeneralUtils utils;


    /**
     * Class constructor
     * @param driver
     */
    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
        utils = new GeneralUtils(this.driver);
        utils.maximize();
    }


    /**
     * Check page
     * @return check
     */
    public Boolean loadPage(){
        return utils.isDisplayed(btnSubmit);
    }


    /**
     * Enter Data
     * @param dto
     */
    public void register (FormDto dto) {
        utils.input(inPetName, dto.getPetName());
        utils.input(inOwnerName, dto.getOwnerName());
        utils.input(inDate, dto.getDate());
        utils.input(inTime, dto.getTime());
        utils.input(inSymptoms, dto.getSymptoms());

    }

    /**
     * Submit register
     */
    public Boolean submit ()  {
        try {
            utils.click(btnSubmit);
            return true;
        } catch (TimeoutException var) {
            System.out.println(var);
            return false;
        }
    }


    /**
     * validate schedule
     * @param dto
     * @return
     */
    public Boolean checkScheduled (FormDto dto){
        try {
            utils.getText(By.xpath(startTextField + dto.getPetName() + endTextField));
            utils.getText(By.xpath(startTextField + dto.getOwnerName() + endTextField));
            utils.getText(By.xpath(startTextField + dto.getSymptoms() + endTextField));
            utils.getText(By.xpath(startTextField + dto.getTime().substring(0,5) + endTextField));

            LocalDate dateAppointment = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            utils.getText(By.xpath(startTextField + dateAppointment.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + endTextField));
            return true;
        } catch (TimeoutException var) {
            System.out.println(var);
            return false;
        }
    }


    /**
     * Delete Appointment
     */
    public Boolean deleteAppointment ()  {
        try {
            utils.click(btnDelete);
            return true;
        } catch (TimeoutException var) {
            System.out.println(var);
            return false;
        }
    }

    /**
     * Check error message
     * @return check
     */
    public Boolean checkMessage(){
        try {
            utils.isDisplayed(alertError);
            return true;
        } catch (TimeoutException var) {
            System.out.println(var);
            return false;
        }
    }

}
