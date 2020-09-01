package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

abstract public class MyListsPageObject extends MainPageObject
{

    protected static String
            FOLDER_BY_NAME_TPL,
            SNACK_BAR,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_SEARCH_RESULTS,
            DELETE_BUTTON,
            ARTICLE_TITLE;

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleXpathByName(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder)
    {
        this.waitForElementNotPresent(
                SNACK_BAR,
                "SnackBar still presents",
                15
        );
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String title_xpath = getArticleXpathByName(article_title);
        this.waitForElementPresent(
                title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String title_xpath = getArticleXpathByName(article_title);
        this.waitForElementNotPresent(
                title_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String title_xpath = getArticleXpathByName(article_title);
        this.swipeElementToLeft(
                title_xpath,
                "Cannot find saved article"
        );
        if(Platform.getInstance().isIos()){
            waitForElementAndClick(DELETE_BUTTON, "Cannot find delete button", 5);
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountsOfArticles(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        return this.getAmountOfElements(ARTICLE_SEARCH_RESULTS);
    }

    public int getAmountOfElements(){
        return this.getAmountOfElements(ARTICLE_SEARCH_RESULTS);
    }

    public String getArticleTitle()
    {
        WebElement item_title = this.waitForElementPresent(
                ARTICLE_TITLE,
                "Cannot find saved article title",
                15
        );

        return item_title.getAttribute("text");
    }

    public void clickByArticle()
    {
        this.waitForElementAndClick(
                ARTICLE_TITLE,
                "Cannot find saved article",
                5
        );
    }

    public void assertArticlesSubtitle(String article_title, String expected_subtitle)
    {
        this.waitForElementPresent(ARTICLE_TITLE,"Cannot find article by substring " + ARTICLE_TITLE, 5);
        Assert.assertEquals(
                "We see unexpected title",
                expected_subtitle,
                article_title
        );
    }
}
