import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Alexandra on 27/01/2017.

 Scenario 1 for:
 1. Login
 2. Navigate to Admin => Structure
 3. Add new Client
 4. Add new Division
 5. Add new Academy
 6. Add new Activity

 Post-conditions: delete testing objects (clear db)
 */


public class Scenario_1 {

    LoginPage loginPage;
    Structure structure;

    private WebDriver driver;
    private WebDriverWait wait;

    //Input test variables:
    private String browser = "Chrome"; //"Chrome" (only), "Mozilla", "IE"
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";

    private String client = "Test Client 10";
    private String client_director = "Mr Simon Martin";
    private String client_contact_name = "Test Contact";
    private String client_contact_email = "test-client@imparta.com";
    private Boolean is_appear_on_reports = true;

    private String division = "Test Division 10";
    private String div_adress1 = "Test Address 1 string";
    private String div_adress2 = "Test Address 2 string";
    private String div_adress3 = "Test Address 3 string";
    private String div_postcode = "SW6 3BN";
    private String div_city = "Test city";
    private String div_country = "United Kingdom";
    private String div_phone = "07777777777";

    private String academy = "Test Academy 10";
    private String ac_language = "English (United States)";

    private String activity = "Test Activity 10";
    private String act_type = "Enable";
    private String act_lang = "English (United States)";
    private String act_date = "08/03/2018 00:00";

    private String usr_email = "alexandra.ilianova@imparta.com";
    private String usr_firstName = "Alexandra";
    private String usr_lastName = "Ilianova";



    @Before
    public void start() {

        switch (browser) {
            case "Chrome":
                //System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                System.out.println("Selected browser:    Google Chrome");
                break;
            case "Mozilla":
                System.setProperty("webdriver.gecko.driver", "C:\\Tools\\selenium drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println("Selected browser:    Mozilla Firefox");
                break;
            case "IE":
                //System.setProperty("webdriver.ie.driver", "C:\\Tools\\selenium drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                System.out.println("Selected browser:    Internet Explorer");
                break;
        }
        System.out.println("------------- Scenario execution: ------------- ");
        wait = new WebDriverWait(driver, 60);
    }

    @Test
    //Scenario 1:
    // Testing creating Structure elements: a client, a division, an academy, an activity (Enable)
    // Enrolling a participant for the activity

    public void Scenario_1() {

        //1. Login
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(home_url, user, password);

        //2. Navigating to Admin => Structure ----------------------------------------------------------------------------------
        structure = new Structure(driver);
        structure.openStructure();

        //3. Add new client ----------------------------------------------------------------------------------------------------
        structure.addClient(
                client,
                client_director,
                is_appear_on_reports,
                client_contact_name,
                client_contact_email);

        //4. Add new division --------------------------------------------------------------------------------------------------
        structure.addDivision(
                division,
                div_adress1,
                div_adress2,
                div_adress3,
                div_postcode,
                div_city,
                div_country,
                div_phone);

        //5. Add new academy --------------------------------------------------------------------------------------------------
        structure.addAcademy(
                academy,
                ac_language);

        //6. Add new activity --------------------------------------------------------------------------------------------------
        structure.addActivity(
                activity,
                act_type,
                act_lang,
                act_date);

        //7. Add participant --------------------------------------------------------------------------------------------------
        structure.addParticipant(
                usr_email,
                usr_firstName,
                usr_lastName);

        //8. Search for the academy in Structure --------------------------------------------------------------------------------------------------

  /*      wait.until(presenceOfElementLocated(By.id("iCoachNG_anchor"))).click();
        wait.until(presenceOfElementLocated(By.className("form-field-md"))).sendKeys(usr_email);
        wait.until(presenceOfElementLocated(By.id("searchall-btn"))).click();
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"search-all-clients-results\"]/li"))).click();

        String txt = usr_firstName + " " + usr_lastName + " (" + usr_email + ") - (" + academy + "\\" + activity + ")";
        String txt2 = "Alexandra Ilianova (alexandra.ilianova@imparta.com) - (Imparta Internal\\Activity_24_Diagnostic with SP_4766)";
        //"Alexandra Ilianova (alexandra.ilianova@imparta.com) - (Test Academy 10\Test Activity 10)";
        WebElement el = driver.findElement(By.xpath("//div[@id = 'search-all-results']/descendant::li[text() = '" + txt2 + "']"));
       // el.click();

*/
        /*
String text = "AppraisersGroupTest";
WebElement el = driver.findElement(By.xpath("//div[@id = 'colLeft_OrderGroups']/descendant::li[text() = '" + text + "']"));
el.click();
http://stackoverflow.com/questions/38212644/selenium-select-item-from-list-by-the-ul-li-value-text
        */

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}

// Assert.assertEquals(element.getAttribute(attributeName), expectedAttributeValue);