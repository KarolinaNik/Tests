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

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
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
 6. Add new Activity (@activity)

 Post-conditions: clear db
 delete from OrgStructure where ActivityID in (select ID from Activities where Title = 'Test Activity 10')
 delete from Activities where Title = 'Test Activity 10'
 delete from Academies  where Title = 'Test Academy 10'
 delete from Divisions where Name = 'Test Division 10'
 delete from Clients where Name='Test Client 10'
 */


public class Scenario_1 {

    //
    LoginPage loginPage;
    private WebDriver driver;
    private WebDriverWait wait;
    //Input variables declaring:
    private String browser = "Chrome"; //"Chrome", "Mozilla", "IE"
    private String home_url = "https://iportal-integration.azurewebsites.net/ng/Login";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "AZsxdc1234";

    private String client = "Test Client 10";
    private String client_director = "Mr Simon Martin";
    private String client_contact_name = "Test Contact";
    private String client_contact_email = "test-client@imparta.com";
    private Boolean is_appear_on_reports = true;

    private String division = "Test Division 10";
    private String div_adress1 = "Test Adress 1 string";
    private String div_adress2 = "Test Adress 2 string";
    private String div_adress3 = "Test Adress 3 string";
    private String div_postcode = "SW6 3BN";
    private String div_city = "Test city";
    private String div_country = "United Kingdom";
    private String div_phone = "07777777777";

    private String academy = "Test Academy 10";
    private String ac_language = "English (United States)";

    private String activity = "Test Activity 10";
    private String act_type = "Enable";
    private String act_lang = "English (United States)";
    private String act_date = "08/03/2017 00:00";

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
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage(home_url, user, password);
        /*driver.navigate().to(home_url);
        wait.until(presenceOfElementLocated(By.id("UserName"))).sendKeys(user);
        wait.until(presenceOfElementLocated(By.id("Password"))).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();
        */

//2. Navigating to Admin => Structure ----------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.partialLinkText("Admin"))).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Structure"))).click();
        wait.until(titleIs("Admin | Structure"));
        wait.until(presenceOfElementLocated(By.cssSelector("i.fa.fa-bars.fa-2x"))).click();                             //close left menu

//3. Add new client ----------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New client' button

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field-md"))).sendKeys(client);              //Fill in "Client" name

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"SelectedClientDirectorID_chosen\"]/a/div/b"))).click(); //Select Client Director
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + client_director + "']"))).click();

        if (is_appear_on_reports) {
            driver.findElement(By.cssSelector("p.checkbox-row")).click();
        }

        driver.findElement(By.cssSelector("input#ContactName.form-field-md")).sendKeys(client_contact_name);            //Fill in "Contact Name"
        driver.findElement(By.cssSelector("input#ContactEmail.form-field-md")).sendKeys(client_contact_email);          //Fill in "Contact Email"

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit"))).click();  //clicking "Save"

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));   //wait for success message appears
        System.out.println("Added new client:    " + client);                                                           //message in console that client is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));       //wait for success message to disappear

//4. Add new division --------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New Division' button

        wait.until(presenceOfElementLocated(By.cssSelector("input#Name.form-field"))).sendKeys(division);               //Fill in 'Division' name
        driver.findElement(By.cssSelector("input#Address1.form-field-lg")).sendKeys(div_adress1);                       //Fill in 'Address 1'
        driver.findElement(By.cssSelector("input#Address2.form-field-lg")).sendKeys(div_adress2);                       //Fill in 'Address 2'
        driver.findElement(By.cssSelector("input#Address3.form-field-lg")).sendKeys(div_adress3);                       //Fill in 'Address 3'
        driver.findElement(By.cssSelector("input#Postcode.form-field-lg")).sendKeys(div_postcode);                      //Fill in 'Postcode'
        driver.findElement(By.cssSelector("input#City.form-field-lg")).sendKeys(div_city);                              //Fill in 'City'

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Country_chosen\"]/a/span"))).click();    //Select 'Country'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + div_country + "']"))).click();

        driver.findElement(By.cssSelector("input#PhoneNumber.form-field-lg")).sendKeys(div_phone);                      //Fill in 'Phone Number'

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit"))).click();        //click 'Save'

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message to appear
        System.out.println("Added new division:  " + division);                                                                                    //message in console that division is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear


//5. Add new academy --------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New academy' button

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field-lg"))).sendKeys(academy);            //Fill in 'Academy' name
        //driver.findElement(By.cssSelector("input#Title.form-field-lg"))

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CultureString_chosen\"]/a/span"))).click();
        // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".chosen-single > span:nth-child(1)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + ac_language + "']"))).click();    //Select 'Language'

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             // click 'Save'

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message appearing
        System.out.println("Added new academy:   " + academy);                                                                                     //message in console that academy is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear


//6. Add new activity --------------------------------------------------------------------------------------------------
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New activity' button
        //driver.findElement(By.cssSelector("li.icon-stylized-add-white")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field"))).sendKeys(activity);              //Fill in 'Activity Name'

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"activity_setup_product_type_chosen\"]/a/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_type + "']"))).click();       //Select 'Type'

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Culture_chosen\"]/a/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_lang + "']"))).click();       //Select 'Language'

        driver.findElement(By.cssSelector("#Ends")).sendKeys(act_date);                                                 //Select 'End Date'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/button[2]"))).click();

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             //click 'Save'

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message appearing
        System.out.println("Added new activity:  " + activity);                                                                                    //message in console that activity is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

//7. Add participant --------------------------------------------------------------------------------------------------

        wait.until(presenceOfElementLocated(By.cssSelector("#structure-management-toolbar > ul > li.th-menu-white"))).click();
        wait.until(presenceOfElementLocated(By.className("icon-stylized-add-user-white"))).click();

        wait.until(presenceOfElementLocated(By.id("EmailAddress"))).sendKeys(usr_email);
        wait.until(presenceOfElementLocated(By.id("Firstname"))).sendKeys(usr_firstName);
        wait.until(presenceOfElementLocated(By.id("Lastname"))).sendKeys(usr_lastName);

        //has no manager (!)

        wait.until(presenceOfElementLocated(By.cssSelector("#structure-management-toolbar > ul > li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("#structure-management-toolbar > ul > li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             //click 'Save'

        System.out.println("Enrolled new user:   " + usr_firstName + " " + usr_lastName);                                                                                    //message in console that activity is added


    }

    // Assert.assertEquals(element.getAttribute(attributeName), expectedAttributeValue);

    @After
    public void stop() {
        //    driver.quit();
        //    driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}

// To do: all types of activities, enrol participants, launch activity