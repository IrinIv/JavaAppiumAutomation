package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test

    public void testPassThroughtWelcome() {

        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPreferLanguagesLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();




    }


}
