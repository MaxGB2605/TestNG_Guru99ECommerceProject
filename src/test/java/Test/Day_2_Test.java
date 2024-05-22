package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day_2_Test extends CommonMethods {




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
