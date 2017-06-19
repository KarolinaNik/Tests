import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Alexandra on 27/03/2017.
 */
public class CourseFlow {

    WebDriver driver;
    WebDriverWait wait;

    public CourseFlow(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 30);

    }

    public void openCourseFlow() {

        wait.until(presenceOfElementLocated(By.partialLinkText("Admin"))).click();
        wait.until(presenceOfElementLocated(By.partialLinkText("Course Flow"))).click();
        wait.until(titleIs("Admin | Course Flow"));
        wait.until(presenceOfElementLocated(By.id("CourseFlow-new")));

    }

    public void addCourseFlow(String courseflow_name, String courseflow_descr) {
        wait.until(presenceOfElementLocated(By.id("CourseFlow-new"))).click();
        wait.until(presenceOfElementLocated(By.id("Title"))).sendKeys(courseflow_name);              //Fill in "Course Flow" name
        wait.until(presenceOfElementLocated(By.id("Description"))).sendKeys(courseflow_descr);       //Fill in "Course Flow" description

/*
        wait.until(presenceOfElementLocated(By.cssSelector("#frmFlowEditor > div.form-horizontal > input.btn.btn-primary"))).click();
        wait.until(presenceOfElementLocated(By.className("input#Title.form-control"))).sendKeys("Test Section 1");

        wait.until(presenceOfElementLocated(By.className("span.glyphicon.glyphicon-eye-open"))).click();

        wait.until(presenceOfElementLocated(By.cssSelector("#BankThreshold"))).sendKeys("10");
        wait.until(presenceOfElementLocated(By.cssSelector("#BankId"))).sendKeys("1");
*/


        wait.until(presenceOfElementLocated(By.id("CourseFlow-save"))).click();  //clicking "Save"
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));   //wait for success message appears
        System.out.println("Added new Course Flow:    " + courseflow_name);                                                           //message in console that client is added
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notifyjs-bootstrap-base.notifyjs-bootstrap-success")));
    }
}
