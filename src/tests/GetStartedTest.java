package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome()
    {
        if(Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSearchLanguagesText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForHelpText();
        WelcomePage.clickGetStartedButton();
    }
}
