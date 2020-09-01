package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
}
