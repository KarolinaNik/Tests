import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 27/01/2017.

 Scenario 1 for:
 1. Login
 2. Navigate to Admin => Structure
 3. Add new Client (@client)
 4. Add new Division (@division)
 5. Add new Academy (@academy)
 6. Add new Activity (@activity) - not working

 Post-conditions: clear db
 delete from Academies where Title = 'Test Academy 10'
 delete from Divisions where Name = 'Test Division 10'
 delete from Clients where Name = 'Test Client 10'

 */


public class Scenario_1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;
    private String span_script;

    //Input variables definition
    private String browser = "Chrome"; //"Chrome", "Mozilla", "IE" (full screen needed)
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";

    private String client = "Test Client 10";
    private String client_director = "Mr Simon Martin";   //problem in FF!
    private String client_contact_name = "Test Contact";
    private String client_contact_email = "test-client@imparta.com";
    private Boolean is_appear_on_reports = true;

    private String division = "Test Division 10";
    private String div_adress1 = "Test Adress 1 string";
    private String div_adress2 = "Test Adress 2 string";
    private String div_adress3 = "Test Adress 3 string";
    private String div_postcode = "SW6 3BN";
    private String div_city = "Test city";
    private String div_country = "United Kingdom";         //problem in FF!
    private String div_phone = "07777777777";


    private String academy = "Test Academy 10";
    private String activity = "Test Activity 10";

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
    public void Scenario_1() {

//1. Login
        driver.navigate().to(home_url);
        wait.until(presenceOfElementLocated(By.id("UserName")));
        driver.findElement(By.id("UserName")).sendKeys(user);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("span.hidden-xs.usersFullname")));
        //Assert.assertEquals(driver.findElement(By.cssSelector("span.hidden-xs.usersFullname")).getAttribute("innerText"), "Alexandra Ilianova ");
        //Assert.assertTrue(driver.findElement(By.cssSelector("span.hidden-xs.usersFullname")).getAttribute("innerText").contains("Alexandra Ilianova"));


//2. Navigating to Admin => Structure ----------------------------------------------------------------------------------
        driver.findElement(By.partialLinkText("Admin")).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Structure")));
        driver.findElement(By.partialLinkText("Structure")).click();
        wait.until(titleIs("Admin | Structure"));

//3. Add new client ----------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));                             //'New client' button
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field-md")));
        driver.findElement(By.cssSelector("input#Name.form-field-md")).sendKeys(client);                                //Fill in "Client" name

        element = driver.findElement(By.cssSelector("a.chosen-single"));                                                //select a "Client Director"   NOT WORKING IN FF
        span_script = "arguments[0].innerText = '" + client_director + "'";           //((JavascriptExecutor)driver).executeScript("arguments[0].click;", element);
        ((JavascriptExecutor) driver).executeScript(span_script, element);


        driver.findElement(By.cssSelector("input#ContactName.form-field-md")).sendKeys(client_contact_name);            //Fill in "Contact Name"
        driver.findElement(By.cssSelector("input#ContactEmail.form-field-md")).sendKeys(client_contact_email);          //Fill in "Contact Email"

        if (is_appear_on_reports) {
            driver.findElement(By.cssSelector("p.checkbox-row")).click();
        }

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             //clicking "Save"

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));   //wait for success message appears
        System.out.println("Added new client:    " + client);                                                           //message in console that client is added
        //System.out.println("Client's details:" + "    Contact Name=" + client_contact_name + "; Contact Email=" + client_contact_email + "; Client Director=" + client_director + "; Include this client on summary reports=" + is_appear_on_reports);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));       //wait for success message to disappear

//4. Add new division --------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));                             //'New division' button
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field")));
        driver.findElement(By.cssSelector("input#Name.form-field")).sendKeys(division);                                 //Fill in 'Division' name

        driver.findElement(By.cssSelector("input#Address1.form-field-lg")).sendKeys(div_adress1);                       //Fill in 'Address 1'
        driver.findElement(By.cssSelector("input#Address2.form-field-lg")).sendKeys(div_adress2);                       //Fill in 'Address 2'
        driver.findElement(By.cssSelector("input#Address3.form-field-lg")).sendKeys(div_adress3);                       //Fill in 'Address 3'
        driver.findElement(By.cssSelector("input#Postcode.form-field-lg")).sendKeys(div_postcode);                      //Fill in 'Postcode'
        driver.findElement(By.cssSelector("input#City.form-field-lg")).sendKeys(div_city);                              //Fill in 'City'

        element = driver.findElement(By.cssSelector("a.chosen-single"));                                                //select 'Country'   NOT WORKING IN FF
        span_script = "arguments[0].innerText = '" + div_country + "'";
        ((JavascriptExecutor) driver).executeScript(span_script, element);

        driver.findElement(By.cssSelector("input#PhoneNumber.form-field-lg")).sendKeys(div_phone);                      //Fill in 'Phone Number'


        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             //clicking "Save"

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message to appear
        System.out.println("Added new division:  " + division);                                                                                    //message in console that division is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear


//5. Add new academy
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field-lg")));
        driver.findElement(By.cssSelector("input#Title.form-field-lg")).sendKeys(academy);

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message disappearing
        System.out.println("Added new academy:   " + academy);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));


//6. Add new activity
/*        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white")));
        driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field")));
        driver.findElement(By.cssSelector("input#Title.form-field")).sendKeys(activity);

        //driver.findElement(By.cssSelector("a.chosen-single")).sendKeys("Enable");
        //driver.findElement(By.cssSelector("a.chosen-single.chosen-default")).sendKeys("English (United States)");
        //driver.findElement(By.cssSelector("input#Ends.form-field-sm.datetimeinput.hasDatepicker.valid")).sendKeys("07/31/2017 00:00");

        System.out.println("Added new activity: " + activity);
*/
    }

    // Assert.assertEquals(element.getAttribute(attributeName), expectedAttributeValue);

    @After
    public void stop() {
        //  driver.quit();
        //  driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}
