import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void searchTextAndClearTest() {

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search",
                5);


        List<WebElement> search_result = driver.findElements(By.id("org.wikipedia:id/page_list_item_container"));

            System.out.println(search_result.size());
            int article_amount = search_result.size();

            Assert.assertTrue("Cannot find any articles", article_amount > 0);



        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search",
                5);


        waitForElementPresent(By.id("org.wikipedia:id/search_empty_image"),
                "A lot of articles is here still",
                5);

    }

    @Test
    public void searchTextAndVerifyTest() {

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search",
                5);


        List<WebElement> search_result = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

         List<String> seachResultList = new ArrayList<>();

        for(int i = 0; i < search_result.size(); i++) {

            MobileElement seachResult = (MobileElement) search_result.get(i);

            seachResultList.add(seachResult.getAttribute("text"));


            Assert.assertTrue("Word Java does not contain in the search", seachResultList.contains("Java") );
        }

        System.out.println(seachResultList);


    }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {


        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();

        return element;

    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {


        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);

        return element;

    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {


        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();

        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    }
