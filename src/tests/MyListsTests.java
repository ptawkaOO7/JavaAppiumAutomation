package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.MyListsPageObject;
import lib.UI.NavigationUi;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.MyListsPageObjectFactory;
import lib.UI.factories.NavigationUiFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }
}
