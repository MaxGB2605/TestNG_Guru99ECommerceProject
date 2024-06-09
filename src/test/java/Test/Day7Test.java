package Test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.CommonMethods;

import java.time.Duration;

public class Day7Test extends CommonMethods {
    /*
    1. Go to web application
    2. Click on My Account link
    3. Login in application using previously created credential
    4. Click on "My Orders"
    5. Click on "View Order"
    6. Verify the previously created order is displayed in "Recent Orders"
    table and status is Pending
    7. Click on "Print Order" link
    8. Verify Order is saved as PDF
     */

    private String email = "john@doe.com";
    private String password = "password";
    private String country = "United States";
    private String state = "New York";
    private int zipCode = 542896;
    private String address = "ABC";
    private String city = "New York";
    private String phone = "12345678";

    @Test
    public void savePreviousOrder(){

        //2. Click on My Account link
        driver.findElement(By.xpath("(//*[@class='label'])[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.linkText("My Account")).click();

        //3. Login in application using previously created credential
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).click();

       //4. Click on "My Orders"
        driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();

        //5. Click on "View Order"
        //driver.findElement(By.xpath(""))


//6. Verify the previously created order is displayed in "Recent Orders" table and status is Pending



//    7. Click on "Print Order" link



//    8. Verify Order is saved as PDF

    }
}
