package tests.iOS;

import lib.UI.WelcomePageObject;
import lib.iOSTestCase;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void testPassThroughWelcome()
    {
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
