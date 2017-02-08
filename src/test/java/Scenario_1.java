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

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 27/01/2017.
 */


public class Scenario_1 {

    private WebDriver driver;
    private WebDriverWait wait;

    //test scenario input variables definition
    private String browser = "Chrome"; //"Chrome", "Mozilla", "IE"
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";
    private String client = "Test Client 10";
    private String division = "Test Division 10";
    private String academy = "Test Academy 10";
    private String activity = "Test Activity 10";

    @Before
    public void start() {

        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();                        //to start Chrome full screen
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            case "Mozilla":
                System.setProperty("webdriver.gecko.driver", "C:\\Tools\\selenium drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", "C:\\Tools\\selenium drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Scenario_1() {

        //Login
        driver.navigate().to(home_url);
        wait.until(presenceOfElementLocated(By.id("UserName")));
        // wait.until(titleIs("Log in"));
        driver.findElement(By.id("UserName")).sendKeys(user);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();  //class name = "btn btn-success btn-block", Webdriver problem with spaces inside class names
        wait.until(presenceOfElementLocated(By.cssSelector("span.hidden-xs.usersFullname")));
        // driver.findElement(By.cssSelector("span.hidden-xs.usersFullname"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.hidden-xs.usersFullname")).getAttribute("innerText"), "Alexandra Ilianova ");


        //Navigating to Admin => Structure
        driver.findElement(By.partialLinkText("Admin")).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Structure")));
        driver.findElement(By.partialLinkText("Structure")).click();
        wait.until(titleIs("Admin | Structure"));


        //Add new client
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field-md")));
        driver.findElement(By.cssSelector("input#Name.form-field-md")).sendKeys(client);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();

        //Add new division
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field")));
        driver.findElement(By.cssSelector("input#Name.form-field")).sendKeys(division);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();
        //icon-stylized-circle-ok-white StructureFormSubmit

        //Add new academy
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field-lg")));
        driver.findElement(By.cssSelector("input#Title.form-field-lg")).sendKeys(academy);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();


        //Add new activity
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field")));
        driver.findElement(By.cssSelector("input#Title.form-field")).sendKeys(activity);

        //driver.findElement(By.cssSelector("a.chosen-single")).sendKeys("Enable");
        //driver.findElement(By.cssSelector("a.chosen-single.chosen-default")).sendKeys("English (United States)");
        //driver.findElement(By.cssSelector("input#Ends.form-field-sm.datetimeinput.hasDatepicker.valid")).sendKeys("07/31/2017 00:00");

    }

    // Проверки:
    // Assert.assertEquals(element.getAttribute(attributeName), expectedAttributeValue);

    @After
    public void stop() {
        //   driver.quit();
        driver = null;
    }
}
