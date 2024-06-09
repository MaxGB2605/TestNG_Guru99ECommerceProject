package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;

import java.time.Duration;
import java.util.List;

public class Day6Test extends CommonMethods {
    /*
    Verify user is able to purchase product using registered email id(USE CHROME BROWSER)
Test Steps:
1. Go to http://live.techpanda.org/
2. Click on my account link
3. Login in application using previously created credential
4. Click on MY WISHLIST link
5. In next page, Click ADD TO CART link
6. Enter general shipping country, state/province and zip for the shipping cost estimate
7. Click Estimate
8. Verify Shipping cost generated
9. Select Shipping Cost, Update Total
10. Verify shipping cost is added to total
11. Click "Proceed to Checkout"
12. Enter Billing Information, and click Continue
13. In Shipping Method, Click Continue
14. In Payment Information select 'Check/Money Order' radio button. Click Continue
15. Click 'PLACE ORDER' button
16. Verify Oder is generated. Note the order number
     */
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john@doe.com";
    private String password = "password";
    private String country = "United States";
    private String state = "New York";
    private String zipCode = "542896";
    private String address = "ABC";
    private String city = "New York";
    private String phone = "12345678";


    @Test
    public void Test6() {


        //2. Click on my account link
        driver.findElement(By.xpath("(//*[@class='label'])[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.linkText("My Account")).click();

        //3. Login in application using previously created credential
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);

        driver.findElement(By.id("send2")).click();

        //Get current page id
        String currentWindowHandle = driver.getWindowHandle();
        System.out.println("Current window id is : " + currentWindowHandle);

        //4. Click on MY WISHLIST link
        driver.findElement(By.linkText("MY WISHLIST")).click();

        //Switching to next page using window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (currentWindowHandle.equals(windowHandle)) {
                System.out.println("Dashboard window and My Wishlist window id are equal");
            } else {
                System.out.println("Dashboard window and My Wishlist window id are not equal");
            }
        }

        //5. In next page, Click ADD TO CART link
        driver.findElement(By.xpath("//*[@title='Add to Cart']")).click();


        //6. Enter general shipping country, state/province and zip for the shipping cost estimate
        // select country
        WebElement country = driver.findElement(By.xpath("//*[@id='country']"));
        Select countrySelect = new Select(country);
        countrySelect.selectByVisibleText("United States");
        //select state/province
        WebElement state = driver.findElement(By.xpath("//*[@id='region_id']"));
        Select stateSelect = new Select(state);
        stateSelect.selectByVisibleText("New York");
        //enter zipcode
        driver.findElement(By.xpath("//*[@id='postcode']")).clear();
        driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys(zipCode);
        //click estimate
        driver.findElement(By.xpath("//*[@title='Estimate']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //8. Verify Shipping cost generated
        String expectedShippingRate = "Fixed - $5.00";
        String expectedShippingOption = "Flat Rate";
        String actualShippingOption = driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/dl/dt")).getText();
        String actualShippingRate = driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/dl/dd/ul/li/label")).getText();
        System.out.println("Shipping option is: " + actualShippingOption + ". Shipping rate is: " + actualShippingRate);
        Assert.assertEquals(actualShippingRate, expectedShippingRate, "Shipping mismatch");
        Assert.assertEquals(actualShippingOption, expectedShippingOption, "Shipping option mismatch");

        //9. Select Shipping Cost, Update Total
        driver.findElement(By.xpath("//*[@id=\"s_method_flatrate_flatrate\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/div/button/span/span")).click();

        //10. Verify shipping cost is added to total
        String expectedIncludedShipping = "$5.00";
        String actualIncludedShipping = driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[2]/td[2]")).getText();
        System.out.println("Included shipping is: " + actualIncludedShipping);
        Assert.assertEquals(actualIncludedShipping, expectedIncludedShipping, "Added shipping mismatch");

        //11. Click "Proceed to Checkout"
        driver.findElement(By.xpath("(//*[@title = 'Proceed to Checkout'])[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        //12. Enter Billing Information, and click Continue
        try {
            Select selectAddress = new Select(driver.findElement(By.xpath("//*[@id=\"billing-address-select\"]")));
            int selectAddressSize = selectAddress.getOptions().size();
            selectAddress.selectByIndex(selectAddressSize - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("billing:firstname")).clear();
        driver.findElement(By.id("billing:firstname")).sendKeys(firstName);
        driver.findElement(By.id("billing:middlename")).clear();
        driver.findElement(By.id("billing:lastname")).clear();
        driver.findElement(By.id("billing:lastname")).sendKeys(lastName);
        driver.findElement(By.id("billing:company")).clear();
        driver.findElement(By.id("billing:street1")).clear();
        driver.findElement(By.id("billing:street1")).sendKeys(address);
        driver.findElement(By.id("billing:street2")).clear();
        driver.findElement(By.id("billing:city")).clear();
        driver.findElement(By.id("billing:city")).sendKeys(city);
        new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']"))).selectByIndex(43);
        driver.findElement(By.id("billing:postcode")).clear();
        driver.findElement(By.id("billing:postcode")).sendKeys(zipCode);
        new Select(driver.findElement(By.id("billing:country_id"))).selectByVisibleText("United States");
        driver.findElement(By.id("billing:telephone")).clear();
        driver.findElement(By.id("billing:telephone")).sendKeys(phone);


        //13. In Shipping Method, Click Continue
        driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button/span/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String billingAddress = driver.findElement(By.xpath("//*[@id=\"billing-progress-opcheckout\"]/dd/address")).getText();
        String shippingAddress = driver.findElement(By.xpath("//*[@id=\"shipping-progress-opcheckout\"]/dd/address")).getText();
        System.out.println("Billing address : " + billingAddress + ". Shipping address : " + shippingAddress);
        Assert.assertEquals(billingAddress, shippingAddress, "Address is different");

        driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button/span/span")).click();


        //14. In Payment Information select 'Check/Money Order' radio button. Click Continue
        driver.findElement(By.xpath("//*[@id=\"dt_method_checkmo\"]/label")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-buttons-container\"]/button/span/span")).click();

        //WebElement table = driver.findElement(By.xpath("//*[@id=\"checkout-review-table\"]"));
        List<WebElement> webTable = driver.findElements(By.xpath("//*[@id=\"checkout-review-table\"]"));
        for (WebElement table : webTable) {
            System.out.println(table.getText());
        }

        //15. Click 'PLACE ORDER' button
        driver.findElement(By.xpath("//*[@id=\"review-buttons-container\"]/button/span/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //16. Verify Oder is generated. Note the order number
        String orderNumber = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/p[1]/a")).getText();
        System.out.println("Your order number is: " + orderNumber);


    }
}
