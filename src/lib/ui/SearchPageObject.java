package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
                                SEARCH_INPUT = "xpath://*[contains(@text, 'Search')]",
                                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
                                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
                                SEARCH_RESULT_ELEMENT = "id://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
                                EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public static String getResultSearchElement(String substring) {

        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput(){

        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);

        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element",
                5);
    }

    public void waitForCancelButtonToAppeare() {

        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappeare() {

        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch() {

        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }


    public void typeSearchLine(String search_line) {

        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5);

    }

    public void waitForSearchResult(String substring){


        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresent(search_result_xpath,
                "Cannot find search result with substring " + substring,
                15);

    }

    public void clickByArticleWithSubstring(String substring){


        String search_result_xpath = getResultSearchElement(substring);
        waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                15);

    }

    public int getAmountOfFoundArticles() {


        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request ",
                15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLable() {

        this.waitForElementPresent(EMPTY_RESULT_LABEL,
                "Cannot find empty result element",
                15);

    }

    public void assertThereIsNotResultOfSearch() {

        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }
}
