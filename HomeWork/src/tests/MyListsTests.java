package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.MyListsPageObject;
import lib.UI.NavigationUi;
import lib.UI.SearchPageObject;
import lib.UI.factories.MyListsPageObjectFactory;
import lib.UI.factories.NavigationUiFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String name_of_folder = "Learning programming";
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.addArticleToNewList(search_line, "Object-oriented programming language", name_of_folder);
        } else {
            SearchPageObject.addArticlesToMySaved("Object-oriented programming language");
        }

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.addArticleToExistingList(search_line, "Programming language", name_of_folder);
        } else {
            SearchPageObject.addArticlesToMySaved("Programming language");
        }

        SearchPageObject.goToMainPage();

        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        String title = "JavaScript";
        MyListsPageObject.swipeByArticleToDelete(title);

        if(Platform.getInstance().isAndroid()){
            String item_title_text = MyListsPageObject.getArticleTitle();

            Assert.assertEquals(
                    "We see unexpected title",
                    "Object-oriented programming language",
                    item_title_text
            );
        } else {
            MyListsPageObject.assertArticlesSubtitle("Object-oriented programming language", "Object-oriented programming language");
        }

        int amount_of_search_results;
        if (Platform.getInstance().isAndroid()){
            amount_of_search_results = MyListsPageObject.getAmountsOfArticles(title);

        } else {
            amount_of_search_results = MyListsPageObject.getAmountOfElements();
        }
        Assert.assertTrue(
                "We see unexpected results",
                amount_of_search_results == 1
        );
    }
}
