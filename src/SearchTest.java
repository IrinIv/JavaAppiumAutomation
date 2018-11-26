import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
    public void testSearch() {

        WebElement element = driver.findElement(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        element.click();

        WebElement search_field = waitForElementPresent(By.id("org.wikipedia:id/search_src_text") ,
                "Cannot find Search field" ,
                5);

        String search_text = search_field.getAttribute("text");

        Assert.assertEquals("Wrong Search Wikipedia input", "Search…", search_text);
    }


    @Test
    public void testSearchTextAndClear() {

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
    public void testSearchTextAndVerify() {

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

    @Test
    public void testSwipeArticles() {

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find Search",
                5);


        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']") ,
                "Cannot find 'Appium' article in search",
                5);


        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);


        swipeUpTillFindElement(By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of this article",
                20);

    }


    @Test
    public void testSaveArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search",
                5);


        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find 'Java' article in search",
                5);


        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'Got it'",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of my reading list",
                5);

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text to set name of my reading list",
                5);


        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot find button Ok",
                5);


        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Close button",
                5);


        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My List icon",
                5);


        waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder +"']"),
                "Cannot find created folder",
                5);

        swipeElemetToLeft(By.xpath("//*[@text='Java (programming language)']"),
        "Cannot find saved article");


        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5);



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

    private void swipeUp(int timeOfSwipe){

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    private void swipeUpQuick(){
        swipeUp(200);

    }

    private void swipeUpTillFindElement(By by, String error_message, int max_swipes) {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;

            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    private void swipeElemetToLeft(By by, String error_message){

        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getHeight();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    }
