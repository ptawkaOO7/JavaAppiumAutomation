package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MyListsPageObject;
import lib.UI.NavigationUi;
import lib.UI.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String name_of_folder = "Learning programming";
        SearchPageObject.addArticleToNewList(search_line, "Object-oriented programming language", name_of_folder);
        SearchPageObject.addArticleToExistingList(search_line, "Programming language", name_of_folder);
        SearchPageObject.goToMainPage();

        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        String title = "JavaScript";
        int amount_of_search_results = MyListsPageObject.getAmountOfElements(title);

        Assert.assertTrue(
                "We don't find some of the articles in Your list",
                amount_of_search_results == 2
        );

        MyListsPageObject.swipeByArticleToDelete(title);
        String item_title_text = MyListsPageObject.getArticleTitle();
        MyListsPageObject.clickByArticle();

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title",
                item_title_text,
                article_title
        );
    }
}
