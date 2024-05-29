package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;

import java.time.Duration;

public class Day_6_Test extends CommonMethods {
    //Verify user is able to purchase product using registered email id
    //Use Chrome Browser

    private String email = "john@doe.com";
    private String password = "password";
    private String country = "United States";
    private String state = "New York";
    private int zipCode = 542896;
    private String address = "ABC";
    private String city = "New York";
    private String phone = "12345678";


    @Test
    public void loginTest() {


        //2. Click on my account
        driver.findElement(By.xpath("(//*[@class='label'])[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.linkText("My Account")).click();

        //3. Login in application using previously created email
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);

        driver.findElement(By.id("send2")).click();

        //Get current page id
        String currentWindowHandle = driver.getWindowHandle();
        System.out.println("Current window id is : " + currentWindowHandle);

        //4. Click on MY WISHLIST link
        driver.findElement(By.xpath("//*[text()='My Wishlist']")).click();

        //Switching to next page using window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (currentWindowHandle.equals(windowHandle)) {
                System.out.println("Dashboard window and My Wishlist window id are equal");
            } else {
                System.out.println("Dashboard window and My Wishlist window id are not equal");
            }
        }

        //5. Click add to cart
        driver.findElement(By.xpath("//*[@title='Add to Cart']")).click();


        //6. Click proceed to checkout
        driver.findElement(By.xpath("(//*[@title='Proceed to Checkout'])[1]")).click();

        //7. Enter shipping info
        driver.findElement(By.id("billing:street1")).clear();
        driver.findElement(By.id("billing:street1")).sendKeys(address);  //enter address

        driver.findElement(By.id("billing:street1")).clear();
        driver.findElement(By.id("billing:street1")).sendKeys(city);  //enter city







        //5. Verify registration is done
        String actualWelcomeMessage = driver.findElement(By.className("welcome-msg")).getText();
        String expectedWelcomeMessage = "WELCOME, JOHN DOE!";
        System.out.println("Actual message is: " + actualWelcomeMessage + ". Expected message is : " + expectedWelcomeMessage);
        Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage, "Message don't match");

        //6. Go to tv menu
        driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();

        //7. Add LG LCD to wish list
        driver.findElement(By.xpath("(//*[@class='link-wishlist'])[1]")).click();

        //8. Click  share wishlist
        driver.findElement(By.name("save_and_share")).click();

        //9. Send email and message and click share wishlist
        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys("aaa@aaa.com");
        driver.findElement(By.id("message")).clear();
        driver.findElement(By.id("message")).sendKeys("Check this tv out");
        driver.findElement(By.xpath("//*[@title='Share Wishlist']")).click();

        //10. Verify that wishlist is shared
        String expectedShareMessage = "Your Wishlist has been shared.";
        String actualShareMessage = driver.findElement(By.xpath(
                "//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();

        System.out.println("Message of sharing product is: " + actualShareMessage);

        Assert.assertEquals(actualShareMessage, expectedShareMessage, "Don't match");
    }
}
