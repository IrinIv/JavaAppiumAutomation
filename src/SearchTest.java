import io.appium.java_client.MobileElement;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {

        super.setUp();

        MainPageObject = new MainPageObject(driver);
    };


    @Test
    public void testSearch() {

        WebElement element = driver.findElement(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        element.click();

        WebElement search_field = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_src_text") ,
                "Cannot find Search field" ,
                5);

        String search_text = search_field.getAttribute("text");

        Assert.assertEquals("Wrong Search Wikipedia input", "Search…", search_text);
    }


    @Test
    public void testSearchTextAndClear() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search",
                5);


        List<WebElement> search_result = driver.findElements(By.id("org.wikipedia:id/page_list_item_container"));

            System.out.println(search_result.size());
            int article_amount = search_result.size();

            Assert.assertTrue("Cannot find any articles", article_amount > 0);



        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search",
                5);


        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_empty_image"),
                "A lot of articles is here still",
                5);

    }

    @Test
    public void testSearchTextAndVerify() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
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

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find Search",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']") ,
                "Cannot find 'Appium' article in search",
                5);


        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);


        MainPageObject.swipeUpTillFindElement(By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of this article",
                20);

    }


    @Test
    public void testSaveArticleToMyList() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        String search_line1 = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line1,
                "Cannot find Search",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find 'Java' article in search",
                5);


        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' button",
                5);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'Got it'",
                5);

        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of my reading list",
                5);

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text to set name of my reading list",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot find button Ok",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Close button",
                5);

        //add second article
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        String search_line2 = "Pyton";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line2,
                "Cannot find Search",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Python (programming language)']") ,
                "Cannot find 'Pyton' article in search",
                5);


        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' button",
                5);


        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_container"),
                "Cannot find created folder",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Close button",
                5);



        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My List icon",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder +"']"),
                "Cannot find created folder",
                5);


        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
        "Cannot find saved article");


        MainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Python (programming language)']"),
                "Cannot find created folder",
                5);


        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                7);

        String article_title = driver.findElement(By.id("org.wikipedia:id/view_page_title_text")).getAttribute("text");

        Assert.assertEquals("Python (programming language)", article_title);





    }

    @Test
    public void testAmountOfNotEmptySearch() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);

        String search_line = "linkin park discography";

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElementPresent(By.xpath(search_result_locator),
                "Cannot find anything by request " + search_line,
                15);


        int amount_of_search_results = MainPageObject.getAmountOfElements(By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0);

    }


    @Test
    public void testAmountOfEmptySearch() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);

        String search_line = "hfgghfhdj";

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15);



        MainPageObject.assertElementNotPresent(By.xpath(search_result_locator),
                "We have found some results by request " + search_line);
    }


    @Test
    public void testTitleOfArticlePresent() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search",
                5);


        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find 'Java' article in search",
                5);


        String search_title_locator = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";
        //String article_title = driver.findElement(By.id("org.wikipedia:id/view_page_title_text")).getAttribute("text");



        MainPageObject.assertElementPresent(By.xpath(search_title_locator),
                "Title of article doesn't exist");

    }

    @Test
    public void testChangeScreenOrientationOnSearchResult() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search",
                5);



        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find 'Java' topic searching by  in " + search_line,
                15);


        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
               "text",
                "Can not find title of article",
                15);


        driver.rotate(ScreenOrientation.LANDSCAPE);


        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15);

        Assert.assertEquals("Article title has been changed after rotation",
                title_before_rotation, title_after_rotation);


        driver.rotate(ScreenOrientation.PORTRAIT);


        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15);


        Assert.assertEquals("Article title has been changed after rotation",
                title_before_rotation, title_after_second_rotation);


    }

    @Test
    public void testCheckSearchArticleInBackground() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]") ,
                "Cannot find Search field",
                5);


        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search",
                5);



        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find 'Java' topic searching by  in " + search_line,
                15);

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']") ,
                "Cannot find article after returning from background",
                15);




    }




    }
