import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Alexandra on 27/03/2017.
 * Admin => Structure
 * Search for an academy, add activities of
 */
public class Scenario_2 {

    LoginPage loginPage;
    Structure structure;

    private WebDriver driver;
    private WebDriverWait wait;

    //Input test variables:
    private String browser = "Chrome"; //"Chrome", "Mozilla", "IE" (n/a)
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";

    private boolean delete_mode = false;
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
                System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-web-security");
                options.addArguments("--no-proxy-server");

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                driver = new ChromeDriver(options);
                System.out.println("Selected browser:    Google Chrome \n");
                break;
            case "Mozilla":
                System.setProperty("webdriver.gecko.driver", "C:\\Tools\\selenium drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println("Selected browser:    Mozilla Firefox \n");
                break;
            case "IE":
                //System.setProperty("webdriver.ie.driver", "C:\\Tools\\selenium drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                System.out.println("Selected browser:    Internet Explorer \n");
                break;
        }
        System.out.println("------------- Scenario execution: ------------- \n");
        wait = new WebDriverWait(driver, 120);
    }

    @Test
    //Scenario 2:

    public void Scenario_2() {

        //1. Login
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(home_url, user, password);

        //2. Navigating to Admin => Structure ----------------------------------------------------------------------------------
        structure = new Structure(driver);
        structure.openStructure();

        //3. Search for the academy in Structure --------------------------------------------------------------------------------------------------

        wait.until(presenceOfElementLocated(By.id("iCoachNG_anchor"))).click();
        wait.until(presenceOfElementLocated(By.name("searchinput"))).sendKeys(academy);
        wait.until(presenceOfElementLocated(By.id("searchall-btn"))).click();
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"search-all-clients-results\"]/li"))).click();
        wait.until(presenceOfElementLocated(By.className("icon-stylized-delete")));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");                                     //scroll to the top


        //9. Add new activity (CAN BE IN A LOOP) --------------------------------------------------------------------------------------------------
        structure.addActivity(
                //activity,
                "Test activity 10 Course Flow",
                //act_type,
                "Course Flow",
                act_lang,
                act_date);


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