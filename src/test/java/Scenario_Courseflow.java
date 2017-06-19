import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

/**
 * ADD NEW COURSE FLOW
 *
 * Created by Alexandra on 15/05/2017.
 * Creating a new Course Flow
 * Admin => Course Flow
 */
public class Scenario_Courseflow {

    //classes:
    LoginPage loginPage;
    CourseFlow courseFlow;

    //webdriver and wait
    private WebDriver driver;
    private WebDriverWait wait;

    //Input variables declaring:
    private String browser = "Chrome"; //"Chrome", "Mozilla"
    private String home_url = "https://iportal-integration.azurewebsites.net/";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "Qwerty1234";

    private String name = "(test) New Course Flow";
    private String description = "(test) New Course Flow's description";

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
                System.out.println("Selected browser:    Mozilla Firefox");
                break;
        }
        System.out.println("------------- Scenario execution: ------------- ");

        wait = new WebDriverWait(driver, 120);

    }

    @Test
    public void Scenario_Scenario_Courseflow() {

        //1. Login ---------------------------------------------------------------------------------------------------------
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(home_url, user, password);

        //2. Navigating to Admin => Course Flow ----------------------------------------------------------------------------
        courseFlow = new CourseFlow(driver);
        courseFlow.openCourseFlow();

        //3. Add new Course Flow -------------------------------------------------------------------------------------------
        courseFlow.addCourseFlow(name, description);
    }

    @After
    public void stop() {
        //     driver.quit();
        //     driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}
