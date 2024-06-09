package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;

import java.time.Duration;


public class Day5Test extends CommonMethods {

    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john@doe.com";
    private String password = "password";

    @Test
    public void day5Test() throws InterruptedException {

        //2. Click on my account link
        driver.findElement(By.xpath("(//*[@class='label'])[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.linkText("My Account")).click();
//3. Click create account link and fill new user information
        WebElement createAccountBtn = driver.findElement(By.xpath("//*[@title = 'Create an Account']"));
        createAccountBtn.click();

        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys(firstName);

        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys(lastName);

        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys(email);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("confirmation")).clear();
        driver.findElement(By.id("confirmation")).sendKeys(password);

        //4. Click register
        driver.findElement(By.xpath("(//*[@title = 'Register'])[2]")).click();


    }


    @Test
    public void loginTest() {

// Continue to check the user created and verify messages, as system doesn't allow duplicates
        //Click on my account and login
        driver.findElement(By.xpath("(//*[@class='label'])[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.linkText("My Account")).click();

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);

        driver.findElement(By.id("send2")).click();

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
