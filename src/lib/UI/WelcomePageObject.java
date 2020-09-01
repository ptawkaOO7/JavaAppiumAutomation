package lib.UI;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
            STEP_LEARN_MORE_STRING = "id:Learn more about Wikipedia",
            STEP_NEW_WAY_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_SEARCH_LANGUAGES_TEXT = "id:Search in nearly 300 languages",
            STEP_HELP_TEXT = "id:Help make the app better",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_STRING, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForSearchLanguagesText()
    {
        this.waitForElementPresent(STEP_SEARCH_LANGUAGES_TEXT, "Cannot find 'Search in nearly 300 languages' link", 10);
    }

    public void waitForHelpText()
    {
        this.waitForElementPresent(STEP_HELP_TEXT, "Cannot find 'Help make the app better' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' link", 10);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannont find and click 'Skip' button", 5);
    }
}
