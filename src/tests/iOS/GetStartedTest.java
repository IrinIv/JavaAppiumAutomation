package tests.iOS;

import lib.CoreTestCase;
import lib.ui.*;
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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInputIOS();
        SearchPageObject.typeSearchLineIOS("Java");
        SearchPageObject.clickByIOSArticle();

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.addFirstArticleToMyIOSList();
        ArticlePageObject.closeArticleIOS();

        //add second article
        SearchPageObject.initSearchInputIOS();
        SearchPageObject.typeSearchLineIOS("Pyton");
        SearchPageObject.clickByIOSArticle();

        ArticlePageObject.addNextArticleToMyIOSList();
        ArticlePageObject.closeArticleIOS();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyIOSLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        MyListPageObject.selectArticleFromIOSReadingList();
        MyListPageObject.deleteArticleFromIOSLIst();

        


    }


}
