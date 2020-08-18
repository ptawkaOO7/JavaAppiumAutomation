package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testCompareSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "selenium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertAllResultsContainText(search_line);
    }

    @Test
    public void testSearchArticlesAndCancel()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "selenium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertElementContainText(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results > 1
        );
        SearchPageObject.clearSearchResults();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testComparePlaceholderText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String expected_placeholder_text = "Search…";
        SearchPageObject.assertElementHasText(expected_placeholder_text);
    }
}
