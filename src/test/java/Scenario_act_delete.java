import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Delete all activities and above admin objects
 */


public class Scenario_act_delete {

    LoginPage loginPage;
    Structure structure;
    String[] acts = {"Test Activity Coaching Effectiveness",
            "Test Activity Coaching Effectiveness (with Sharepoint)",
            "Test Activity Course Flow",
            "Test Activity Diagnostic",
            "Test Activity Diagnostic with Sharepoint",
            "Test Activity Enable"};
    private WebDriver driver;
    private WebDriverWait wait;
    //Input test variables:
    private String browser = "Chrome"; //"Chrome", "Mozilla"
    private String home_url = "https://iportal-integration.azurewebsites.net/";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "Qwerty1234";
    private boolean delete_mode = true;
    private String client = "Test Client 10";
    private String division = "Test Division 10";
    private String academy = "Test Academy 10";

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
        }
        System.out.println("------------- Scenario execution: ------------- \n");
        wait = new WebDriverWait(driver, 120);
    }

    @Test

    public void Scenario_act_delete() {

        //Login
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(home_url, user, password);

        //Navigating to Admin => Structure ----------------------------------------------------------------------------------
        structure = new Structure(driver);
        structure.openStructure();

        //Deleting activities

        for (int i = 0; i < acts.length; i++) {
            wait.until(presenceOfElementLocated(By.id("iCoachNG_anchor"))).click();
            wait.until(presenceOfElementLocated(By.name("searchinput"))).sendKeys(acts[i]);
            wait.until(presenceOfElementLocated(By.id("searchall-btn"))).click();

            try {
                wait.until(presenceOfElementLocated(By.xpath("/*//*[@id=\"search-all-clients-results\"]/li"))).click();
                wait.until(presenceOfElementLocated(By.className("icon-stylized-delete")));
                structure.deleteActivity(acts[i]);
            } catch (Exception e) {
            }
        }

        structure.deleteAcademy(academy);
        structure.deleteDivision(division);
        structure.deleteClient(client);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}

