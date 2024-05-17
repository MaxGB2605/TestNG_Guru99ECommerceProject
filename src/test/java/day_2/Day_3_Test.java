package day_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.time.Duration;

public class Day_3_Test extends CommonMethods {
   /*
    public static WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/index.php/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    */

    @Test(priority = 1)
    public void day3_Test() {
        WebElement mobileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
        mobileButton.click();
        driver.findElement(By.xpath("(//*[@title='Add to Cart'])[3]")).click();

        WebElement quantity = driver.findElement(By.xpath("//*[@title='Qty']"));
        quantity.clear();
        quantity.sendKeys("1000");
        driver.findElement(By.xpath("(//*[@value=\"update_qty\"])[3]")).click();

        //error message verification
        String expectedMessage = "The requested quantity for \"Sony Xperia\" is not available";
        String actualMessage = driver.findElement(By.xpath("//*[@class='item-msg error']")).getText();
        System.out.println("Expected message - " + expectedMessage + ". Actual message - " + actualMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedMessage, actualMessage, "Message don't match");

//empty cart and verify that shopping cart is empty
        driver.findElement(By.xpath("//*[@id='empty_cart_button']")).click();
        String expectedCartMessage = "SHOPPING CART IS EMPTY";
        String actualCartMessage = driver.findElement(By.xpath("//*[@class = 'page-title']")).getText();
        System.out.println("Expected message - " + expectedCartMessage + ". Actual message - " + actualCartMessage);
        softAssert.assertEquals(expectedCartMessage, actualCartMessage, "Cart message don't match");
        softAssert.assertAll();

    }
/*
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

 */

}
