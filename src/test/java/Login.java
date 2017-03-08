import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 27/01/2017.
 */


public class Login {

    private WebDriver driver;
    private WebDriverWait wait;

    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";
    private String client = "Test Client 10";
    private String division = "Test Division 10";

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void Login() {

        //Login
        driver.navigate().to(home_url);
        wait.until(titleIs("Log in"));
        driver.findElement(By.id("UserName")).sendKeys(user);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();  //class name = "btn btn-success btn-block", Webdriver problem with spaces inside class names
        driver.findElement(By.cssSelector("span.hidden-xs.usersFullname"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.hidden-xs.usersFullname")).getAttribute("innerText"), "Alexandra Ilianova ");

        //Navigating to Admin => Structure
        driver.findElement(By.partialLinkText("Admin")).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Structure")));
        driver.findElement(By.partialLinkText("Structure")).click();
        wait.until(titleIs("Admin | Structure"));

        //add new client
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field-md")));
        driver.findElement(By.cssSelector("input#Name.form-field-md")).sendKeys(client);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();

        //add new division
        wait.until(presenceOfElementLocated(By.cssSelector(" li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector(" li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field")));
        driver.findElement(By.cssSelector("input#Name.form-field")).sendKeys(division);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();

    }

    @After
    public void stop() {
        //  driver.quit();
        driver = null;
    }
}
