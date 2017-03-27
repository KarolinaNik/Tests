import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Alexandra on 27/03/2017.
 * <p>
 * Scenario 2: Admin => Course Flow
 */
public class Scenario_2 {

    //classes:
    LoginPage loginPage;
    CourseFlow courseFlow;

    //webdriver and wait
    private WebDriver driver;
    private WebDriverWait wait;

    //Input variables declaring:
    private String browser = "Chrome"; //"Chrome", "Mozilla", "IE"
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";

    private String name = "(test) New Course Flow";
    private String description = "(test) New Course Flow's description";

    @Before
    public void start() {

        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
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
                System.setProperty("webdriver.ie.driver", "C:\\Tools\\selenium drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                System.out.println("Selected browser:    Internet Explorer");
                break;
        }
        System.out.println("------------- Scenario execution: ------------- ");

        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void Scenario_2() {

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
        // driver.quit();
        // driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}