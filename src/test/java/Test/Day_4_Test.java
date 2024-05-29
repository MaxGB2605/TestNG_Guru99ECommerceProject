package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Day_4_Test extends CommonMethods {


    @Test(priority = 1)
    public void day4_Test() {
        WebElement mobileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
        mobileButton.click();

        //compare phone 1
        driver.findElement(By.xpath("(//*[@class = 'link-compare'])[3]")).click();
        String phone1AddedMsg = driver.findElement(By.xpath("//*[@class = 'success-msg']")).getText();
        System.out.println("First phone added - " + phone1AddedMsg);

        //compare phone 2
        driver.findElement(By.xpath("(//*[@class = 'link-compare'])[1]")).click();
        String phone2AddedMsg = driver.findElement(By.xpath("//*[@class = 'success-msg']")).getText();
        System.out.println("Second phone added - " + phone2AddedMsg);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

//Check we don't have other windows open already
        assert driver.getWindowHandles().size() == 1;

//Click the link which opens in a new window
        driver.findElement(By.xpath("//*[@title='Compare']")).click();

//Wait for the new window or tab
        wait.until(numberOfWindowsToBe(2));

//Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement element = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[1]/h1"));
        System.out.println(element.getText());
        String expectedHeading = "COMPARE PRODUCTS";
        String actualHeading = driver.findElement(By.xpath("//*[@class = 'page-title title-buttons']/h1")).getText();
        Assert.assertEquals(actualHeading, expectedHeading, "Heading don't match");

        //get the list of added items to compare and verify
        List<WebElement> allItems = driver.findElements(By.xpath("//*[@id=\"product_comparison\"]/tbody[1]"));
        int rowCount = allItems.size();
        System.out.println(rowCount);
        for (WebElement itemsElements : allItems) {
            System.out.println(itemsElements.getText());
        }


/*

        String popupWindowHandle= driver.getWindowHandle();
        driver.switchTo().window(popupWindowHandle);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[1]/h1"));
        System.out.println(element.getText());



        String expectedHeading = "COMPARE PRODUCTS";
        String actualHeading=driver.findElement(By.xpath("//*[@class = 'page-title title-buttons']/h1")).getText();
        Assert.assertEquals(expectedHeading,actualHeading,"Headings don't match");

        //get the list of added items to compare and verify
        List<WebElement> allItems = driver.findElements(By.xpath("//*[@class='data-table compare-table']"));
        int rowCount = allItems.size();
        System.out.println(rowCount);



 */
    }
}
