package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {


    private static final String
        FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TEMPLATE = "xpath://*[@text='{TITLE}']",
        BUTTON_EDIT_IOS = "xpath://XCUIElementTypeButton[@name=\"Edit\"]",
        SAVED_ARTICLE_IOS = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell",
        BUTTON_UNSAVE_IOS = "xpath://XCUIElementTypeButton[@name=\"Unsave\"]";



    private static String getFolderXpathByName(String name_of_folder) {

        return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", name_of_folder);
    }


    private static String getSavedArticleXpathByTitle(String article_title) {

        return ARTICLE_BY_TITLE_TEMPLATE.replace("{TITLE}", article_title);
    }



    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);

    }


    public void waitForArticleToDisappearByTitle(String article_title){

        String article_xpath = getFolderXpathByName(article_title);

        this.waitForElementNotPresent(article_xpath,
                "Saved article still present with title " + article_title,
                15);

    }

    public void waitForArticleToAppearByTitle(String article_title){

        String article_xpath = getFolderXpathByName(article_title);

        this.waitForElementPresent(article_xpath,
                "Cannot find saved article by title " + article_title,
                15);

    }


    public void swipeByArticleToDelete(String article_title){

        String article_xpath = getFolderXpathByName(article_title);

        this.waitForArticleToAppearByTitle(article_title);

        this.swipeElementToLeft(article_xpath,
                "Cannot find saved article");

        this.waitForArticleToDisappearByTitle(article_title);


    }


    public void selectArticleFromIOSReadingList() {

        this.waitForElementAndClick(BUTTON_EDIT_IOS,
                "Cannot find and click edit button on iOS",
                5);

        this.waitForElementAndClick(SAVED_ARTICLE_IOS,
                "Cannot find and click saved article on iOS",
                5);
    }


    public void deleteArticleFromIOSLIst() {

        this.waitForElementAndClick(BUTTON_UNSAVE_IOS,
                "Cannot find and click Unsave button on iOS",
                5);


    }
}
