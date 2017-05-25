import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Alexandra on 17/03/2017.
 */
public class Structure {
    WebDriver driver;
    WebDriverWait wait;

    public Structure(WebDriver currentDriver) {

        this.driver = currentDriver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openStructure() {

        wait.until(presenceOfElementLocated(By.partialLinkText("Admin"))).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Structure"))).click();
        wait.until(titleIs("Admin | Structure"));
        wait.until(presenceOfElementLocated(By.cssSelector("i.fa.fa-bars.fa-2x"))).click();
    }

    public void addClient(String client, String client_director, Boolean is_appear_on_reports, String client_contact_name, String client_contact_email) {

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
        System.out.println("Added new client:    " + client);                                                                                        //message in console that client is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));       //wait for success message to disappear

        System.out.println("                     Client Director: " + client_director);
        System.out.println("                     Contact Name:    " + client_contact_name);
        System.out.println("                     Contact Email:   " + client_contact_email + "\n");
    }

    public void deleteClient(String client) {
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-delete"))).click();                     //'Delete client' button

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for deletion success message appearing
        System.out.println("Deleted client:      " + client + "\n");                                                                                          //message in console that client is deleted
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

    }

    public void addDivision(String division, String div_adress1, String div_adress2, String div_adress3, String div_postcode, String div_city, String div_country, String div_phone) {

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

        System.out.println("                     Address 1:       " + div_adress1);
        System.out.println("                     Address 2:       " + div_adress2);
        System.out.println("                     Address 3:       " + div_adress3);
        System.out.println("                     Postcode:        " + div_postcode);
        System.out.println("                     City:            " + div_city);
        System.out.println("                     Country:         " + div_country);
        System.out.println("                     Phone:           " + div_phone + "\n");

    }

    public void deleteDivision(String division) {
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-delete"))).click();                     //'Delete division' button

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for deletion success message appearing
        System.out.println("Deleted division:    " + division);                                                                                      //message in console that division is deleted
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

    }

    public void addAcademy(String academy, String ac_language) {

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New academy' button
        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field-lg"))).sendKeys(academy);            //Fill in 'Academy' name

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CultureString_chosen\"]/a/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + ac_language + "']"))).click();    //Select 'Language'
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             // click 'Save'
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message appearing
        System.out.println("Added new academy:   " + academy);                                                                                     //message in console that academy is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

        System.out.println("                     Language:        " + ac_language);
        System.out.println("                     Results viewers: None");
        System.out.println("                     Icons:           None \n");

    }

    public void deleteAcademy(String academy) {
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-delete"))).click();                     //'Delete activity' button

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for deletion success message appearing
        System.out.println("Deleted academy:     " + academy);                                                                                        //message in console that academy is deleted
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

    }

    public void addActivity(String activity, String act_type, String act_lang, String act_date, String sp_url) {

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-add-white"))).click();                     //'New activity' button
        wait.until(presenceOfElementLocated(By.cssSelector("input#Title.form-field"))).sendKeys(activity);              //Fill in 'Activity Name'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"activity_setup_product_type_chosen\"]/a/span"))).click();

        if (act_type == "Enable") {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_type + "']"))).click();       //Select 'Type'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Culture_chosen\"]/a/span"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_lang + "']"))).click();       //Select 'Language'
            driver.findElement(By.cssSelector("#Ends")).sendKeys(act_date);                                                 //Select 'End Date'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/button[2]"))).click();
        }
        ;

        if (act_type == "Course Flow") {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_type + "']"))).click();       //Select 'Type'

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Culture_chosen\"]/a/span"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_lang + "']"))).click();       //Select 'Language'
            driver.findElement(By.cssSelector("#Ends")).sendKeys(act_date);                                                 //Select 'End Date'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/button[2]"))).click();

            WebElement element = driver.findElement(By.cssSelector("#Ends"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

            String courseFlow = "(test) New Course Flow";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"activity-setup-tbl\"]/tbody/tr/td[2]/div/div/div/a/span"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + courseFlow + "']"))).click();

            String genCertificates = "Yes";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ActivityParameter_GenerateCertificates_chosen\"]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + genCertificates + "']"))).click();

        }
        ;

        if ((act_type == "Diagnostic") || (act_type == "Diagnostic (with Sharepoint)")) {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_type + "']"))).click();       //Select 'Type'

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Culture_chosen\"]/a/span"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + act_lang + "']"))).click();       //Select 'Language'
            driver.findElement(By.cssSelector("#Ends")).sendKeys(act_date);                                                 //Select 'End Date'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/button[2]"))).click();

            WebElement element = driver.findElement(By.cssSelector("#Ends"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

            //activity specific details
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_NumberOfnominees"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_NumberOfnominees"))).sendKeys("2");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_Contact"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_Contact"))).sendKeys("Test Contact Name");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_ContactEmail"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_ContactEmail"))).sendKeys("TestContactEmail@email.com");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_InviteSubject"))).clear();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_InviteSubject"))).sendKeys("Test invitiation subject");

            // wait.until(ExpectedConditions.elementToBeClickable(By.id("ActivityParameter_Model"))).click();

            WebElement fileInput = driver.findElement(By.id("ActivityParameter_Model"));
            fileInput.sendKeys("C:/Tools/selenium_input/Superhero Effectiveness.xml");

            if (act_type == "Diagnostic (with Sharepoint)") {
                wait.until(ExpectedConditions.elementToBeClickable(By.className("textarea.form-field.display-urlbox"))).sendKeys(sp_url);
            }
            ;

        }
        ;

        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();             //click 'Save'
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for success message appearing
        System.out.println("Added new activity:  " + activity);                                                                                    //message in console that activity is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear
        wait.until(presenceOfElementLocated(By.cssSelector("i.fa.fa-bars.fa-2x"))).click();                             //close left navigation bar
        System.out.println("                     Type:            " + act_type);
        System.out.println("                     Language:        " + act_lang);
        System.out.println("                     End Date:        " + act_date + "\n");
    }

    public void deleteActivity(String activity) {
        wait.until(presenceOfElementLocated(By.cssSelector("li.icon-stylized-delete"))).click();                     //'Delete activity' button

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success"))); //wait for deletion success message appearing
        System.out.println("Deleted activity:    " + activity);                                                                                      //message in console that activity is deleted
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));     //wait for success message to disappear

    }

    public void addParticipant(String usr_email, String usr_firstName, String usr_lastName) {
        wait.until(presenceOfElementLocated(By.cssSelector("#structure-management-toolbar > ul > li.th-menu-white"))).click();
        wait.until(presenceOfElementLocated(By.className("icon-stylized-add-user-white"))).click();
        wait.until(presenceOfElementLocated(By.id("EmailAddress"))).sendKeys(usr_email);
        wait.until(presenceOfElementLocated(By.id("FirstName"))).sendKeys(usr_firstName);
        wait.until(presenceOfElementLocated(By.id("LastName"))).sendKeys(usr_lastName);

        //has no manager (!)

        wait.until(presenceOfElementLocated(By.cssSelector("#structure-management-toolbar > ul > li.icon-stylized-circle-ok-white.StructureFormSubmit")));
        driver.findElement(By.cssSelector("#structure-management-toolbar > ul > li.icon-stylized-circle-ok-white.StructureFormSubmit")).click();       //click 'Save'
        System.out.println("Enrolled new user:   " + usr_firstName + " " + usr_lastName + "\n");                                                       //message in console that activity is added
    }

    public boolean searchStructure(String object) {
        wait.until(presenceOfElementLocated(By.id("iCoachNG_anchor"))).click();
        wait.until(presenceOfElementLocated(By.name("searchinput"))).sendKeys(object);
        wait.until(presenceOfElementLocated(By.id("searchall-btn"))).click();
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"search-all-clients-results\"]/li"))).click();
        wait.until(presenceOfElementLocated(By.className("icon-stylized-delete")));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");


        wait.until(presenceOfElementLocated(By.id("iCoachNG_anchor"))).click();
        wait.until(presenceOfElementLocated(By.name("searchinput"))).sendKeys(object);
        wait.until(presenceOfElementLocated(By.id("searchall-btn"))).click();

        try {
            wait.until(presenceOfElementLocated(By.xpath("/*//*[@id=\"search-all-clients-results\"]/li"))).click();
            wait.until(presenceOfElementLocated(By.className("icon-stylized-delete")));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

}
