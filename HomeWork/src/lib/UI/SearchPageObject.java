package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            PLACEHOLDER_TEXT,
            SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_LIST,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            ITEM_TITLE,
            FIRST_ITEM_TITLE,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_NAME_TPL,
            BACK_BUTTON;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getMyListElement(String substring)
    {
        return MY_LIST_NAME_TPL.replace("{SUBSTRING}", substring);
    }

    /* TEMPLATES METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void assertElementHasText(String expected_placeholder_text)
    {
        this.assertElementHasText(PLACEHOLDER_TEXT, expected_placeholder_text, "Cannot find placeholder 'Search…' in the search toolbar");
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void clearSearchResults()
    {
        this.waitForElementAndClear(PLACEHOLDER_TEXT, "Cannot find search field", 5);
        this.waitForElementNotPresent(SEARCH_RESULT_LIST, "Search results are still present on the page",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void assertAllResultsContainText(String search_line)
    {
        this.assertAllElementsContainText(ITEM_TITLE, search_line, 5);
    }

    public void assertElementContainText(String search_line)
    {
        this.assertElementContainText(FIRST_ITEM_TITLE, search_line, "Cannot find 'selenium' in the search results"
        );
    }

    public void addArticleToNewList(String search_line, String substring, String name_of_folder)
    {
        String search_result_xpath = getResultSearchElement(substring);

        this.longTap(
                search_result_xpath,
                "Cannot find the article with a description " + substring + " searching by " + search_line
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticlesToMySaved(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.swipeElementToLeft(
                search_result_xpath,
                "Cannot find article by name"
        );
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void addArticleToExistingList(String search_line, String substring, String name_of_folder)
    {
        String search_result_xpath = getResultSearchElement(substring);

        this.longTap(
                search_result_xpath,
                "Cannot find the article with a description " + substring + " searching by " + search_line
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10
        );

        String myListElementXpath = getMyListElement(name_of_folder);
        this.waitForElementAndClick(
                myListElementXpath,
                "Cannot find created folder",
                5
        );
    }

    public void goToMainPage()
    {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find Back button",
                5
        );
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }
}
