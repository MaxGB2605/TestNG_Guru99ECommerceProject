package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class CommonMethods {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void startBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/index.php/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
