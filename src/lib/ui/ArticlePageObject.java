package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            TITLE_IOS = "xpath://XCUIElementTypeLink[@name=\"Java (programming language) Object-oriented programming language\"]",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            MY_SAVED_LIST = "id:org.wikipedia:id/item_container",
            CLOSE_DIALOG_IOS_BUTTON = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@name=\"places auth close\"]",
            READIND_LIST_IOS_BUTTON = "xpath://XCUIElementTypeToolbar[@name=\"Toolbar\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@name=\"Save for later\"]",
            BACK_IOS_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Back\"]",
            TABLE_OF_CONTENTS_IOS = "xpath://XCUIElementTypeButton[@name=\"Table of contents\"]",
            NAME_OF_CONTENT_IOS = "xpath://XCUIElementTypeStaticText[@name=\"Java (programming language)\"]";




    public WebElement waitForTitleElement() {

        return this.waitForElementPresent(TITLE,
                "Cannot find article title on page",
                15);
    }

    public WebElement waitForTitleElementIOS() {

        return this.waitForElementPresent(TITLE_IOS + "/..",
                "Cannot find article title on IOS screen",
                15);


    }

    public String getArticleTitle() {

        WebElement title_element = waitForTitleElement();

        return title_element.getAttribute("text");
    }


    public String getArticleTitleIOS() {

        WebElement title_element = waitForTitleElementIOS();

        return title_element.getAttribute("name");
    }


    public void swipeToFooter(){

        this.swipeUpTillFindElement(FOOTER_ELEMENT,
                "Cannot find the end of this article",
                20);


    }

    public void addFirstArticleToMyList(String name_of_folder) {


        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button 'More options'",
                5);


        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button 'Got it'",
                5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set name of my reading list",
                5);



        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text to set name of my reading list",
                5);


        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find button Ok",
                5);

    }

        public void addFirstArticleToMyIOSList() {


        this.waitForElementAndClick(READIND_LIST_IOS_BUTTON,
                "Cannot find button 'Reading List' on iOS",
                5);

        this.waitForElementAndClick(CLOSE_DIALOG_IOS_BUTTON,
                    "Cannot find button 'Close dialog' on iOS",
                    5);


        }

    public void addNextArticleToMyList(String name_of_folder) {


        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button 'More options'",
                5);


        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                5);


        this.waitForElementAndClick(MY_SAVED_LIST,
                "Cannot find created folder",
                5);


    }

    public void addNextArticleToMyIOSList() {


        this.waitForElementAndClick(READIND_LIST_IOS_BUTTON,
                   "Cannot find button 'Reading List' on iOS",
                   5);


       }

    public void closeArticle() {

        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find Close button",
                5);

    }


    public void closeArticleIOS() {

        this.waitForElementAndClick(BACK_IOS_ARTICLE_BUTTON,
                "Cannot find Close button",
                5);

    }


}


