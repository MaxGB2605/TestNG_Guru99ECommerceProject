package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;


public class Day1Test extends CommonMethods {


    @Test
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


}
