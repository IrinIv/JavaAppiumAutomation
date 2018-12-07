package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test

    public void testPassThroughtWelcome() {

        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPreferLanguagesLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();




    }


}
