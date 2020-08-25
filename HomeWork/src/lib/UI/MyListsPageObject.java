package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject
{

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/reading_list_list']//*[@text='{FOLDER_NAME}']",
            SNACK_BAR = "id:org.wikipedia:id/snackbar_text",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']",
            ARTICLE_SEARCH_RESULTS = "xpath://*[@resource-id='org.wikipedia:id/reading_list_contents']//*[@resource-id='org.wikipedia:id/page_list_item_container']",
            ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";

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
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountsOfElements(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
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
}
