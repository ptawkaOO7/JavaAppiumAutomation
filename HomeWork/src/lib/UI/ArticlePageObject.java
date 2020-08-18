package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            CLOSE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void assertTitlePresent()
    {
        this.waitForElementPresent(
                By.xpath(CLOSE_BUTTON),
                "Cannot open article, cannot find X link",
                5
        );

        this.assertElementPresent(
                By.id(TITLE),
                "Cannot find article title"
        );
    }
}
