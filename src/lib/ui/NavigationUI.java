package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
        MY_IOS_LIST = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTabBar/XCUIElementTypeButton[@name=\"Saved\"]";


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find My List icon",
                5);

    }

    public void clickMyIOSLists() {

        this.waitForElementAndClick(MY_IOS_LIST,
                "Cannot find My List icon on iOS",
                5);
    }
}
