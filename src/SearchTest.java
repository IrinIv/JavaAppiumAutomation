import io.appium.java_client.MobileElement;
import lib.CoreTestCase;
import lib.ui.*;
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
    }


    @Test
    public void testSearch() {


        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }


    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppeare();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappeare();


    }

    @Test
    public void testSearchTextAndVerify() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);


    }

    @Test
    public void testSwipeArticles() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();


    }


    @Test
    public void testSaveArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        //add second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Pyton");
        SearchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addNextArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);


    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();


        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0);

    }


    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "hfgghfhdj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLable();
        SearchPageObject.assertThereIsNotResultOfSearch();


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
