package day_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day_2_Test extends CommonMethods {
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
    public void dashboardTest() {

        System.out.println(driver.getTitle());

        WebElement welcomeMessage = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div/h2"));
        String expectedMessage = "THIS IS DEMO SITE";
        String actualMessage = welcomeMessage.getText();

        StringBuffer sb1 = new StringBuffer(expectedMessage);
        StringBuffer sb2 = new StringBuffer(actualMessage);
        if (sb1.length() != sb2.length()) {
            System.out.println("mismatch: expected message is "+sb1.length()+" characters" +
                    ", and actual message "+sb2.length()+" characters");
        } else {
            System.out.println("Match");
        }

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage, expectedMessage, "Welcome message mismatch");
        System.out.println("Expected - " + expectedMessage + ": Actual - " + actualMessage);

        //mobile button click and verify title of mobile page
        WebElement mobileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
        mobileButton.click();
        System.out.println(driver.getTitle());
        //verification of the title
        String expectedMessageMobile = "MOBILE";
        String actualMessageMobile = driver.getTitle();
        softAssert.assertEquals(expectedMessageMobile, actualMessageMobile, "Message mismatch");

        softAssert.assertAll();

    }

    @Test(priority = 2)
    public void mobilePageTest() {
        //from home page to get to mobile page
        WebElement mobileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
        mobileButton.click();

        WebElement sortButton = driver.findElement(By.xpath("(//*[@selected = 'selected'])[1]"));
        sortButton.click();

        //getting all the items on the page
        List<WebElement> allItems = driver.findElements(By.xpath("//*[@class = 'product-name']"));
        List<String> phoneNames = new ArrayList<>();  //create an array to store and sort phones for verification
        //iterate through all elements
        for (WebElement item : allItems) {
            System.out.println(item.getText());
            phoneNames.add(item.getText());
            String singlePhoneName = item.getText();
        }
        //verify that items are sorted in alphabetical order
        List<String> sortedPhoneNames = new ArrayList<>(phoneNames);
        Collections.sort(sortedPhoneNames);
        String previousName = ""; //declare temp var to store names
        for (String phoneName : sortedPhoneNames) {
            Assert.assertTrue(phoneName.compareToIgnoreCase(previousName) >= 0);
            previousName = phoneName;
        }
        System.out.println("============================================");
        //lower sort button test
        driver.navigate().refresh();
        WebElement sortButtonBottom = driver.findElement(By.xpath("(//*[@selected = 'selected'])[3]"));
        sortButtonBottom.click();

        List<WebElement> allPhones = driver.findElements(By.xpath("//*[@class = 'product-name']"));

        List<String> sortedItemNames = allPhones.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        String previose = "";
        for (String name : sortedItemNames) {
            System.out.println(name);
            Assert.assertTrue(name.compareToIgnoreCase(previose) >= 0);
            previose = name;
        }

    }

    @Test(priority = 3)
    public void costOfProductVerification() {

        WebElement mobileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
        mobileButton.click();

        WebElement iphonePrice = driver.findElement(By.xpath("//*[@id=\"product-price-2\"]"));
        String iphonePriceFront = iphonePrice.getText();
        WebElement samsungPrice = driver.findElement(By.xpath("//*[@id=\"product-price-3\"]"));
        String samsungPriceFront = samsungPrice.getText();
        WebElement sonyPrice = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]"));
        String sonyPriceFront = sonyPrice.getText();
        System.out.println("Iphone price: " + iphonePrice.getText());
        System.out.println("Samsung price: " + samsungPrice.getText());
        System.out.println("Sony price: " + sonyPrice.getText());

        System.out.println("===========================================");

        //click on the item to go to details and get price from details page
        WebElement iphoneClick = driver.findElement(By.xpath("//*[@id = 'product-collection-image-2']"));
        iphoneClick.click();

        WebElement iphoneDetailsPage = driver.findElement(By.xpath("//*[@id=\"product-price-2\"]"));
        String iphoneDetailsPrice = iphoneDetailsPage.getText();
        System.out.println(iphoneDetailsPage.getText());
        driver.navigate().back();

        WebElement samsungClick = driver.findElement(By.xpath("//*[@id = 'product-collection-image-3']"));
        samsungClick.click();
        WebElement samsungDetailsPage = driver.findElement(By.xpath("//*[@id=\"product-price-3\"]"));
        String samsungDetailsPrice = samsungDetailsPage.getText();
        System.out.println(samsungDetailsPage.getText());
        driver.navigate().back();

        WebElement sonyClick = driver.findElement(By.xpath("//*[@id = 'product-collection-image-1']"));
        sonyClick.click();
        WebElement sonyDetailsPage = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]"));
        String sonyDetailsPrice = sonyDetailsPage.getText();
        System.out.println(sonyDetailsPage.getText());
        driver.navigate().back();


        // Assert that the prices on the list page and details page are the same
        Assert.assertEquals(iphonePriceFront, iphoneDetailsPrice, "Iphone price mismatch");
        Assert.assertEquals(samsungPriceFront, samsungDetailsPrice, "Samsung price mismatch");
        Assert.assertEquals(sonyPriceFront, sonyDetailsPrice, "Sony price mismatch");


    }
/*
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

 */
}
