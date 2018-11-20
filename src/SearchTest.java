import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/Irina/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @After
    public void tearDown() {

        driver.quit();
    }


    @Test
    public void searchTest() {

        WebElement element = driver.findElement(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        element.click();

        WebElement search_field = waitForElementPresent(By.id("org.wikipedia:id/search_src_text") ,
                "Cannot find Search field" ,
                5);

        String search_text = search_field.getAttribute("text");

        Assert.assertEquals("Wrong Search Wikipedia input", "Search…", search_text);
    }






    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

}
