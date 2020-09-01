package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]";
        ARTICLE_SEARCH_RESULTS = "xpath://XCUIElementTypeCell";
        ARTICLE_TITLE = "id:Object-oriented programming language";
        DELETE_BUTTON = "id:swipe action delete";
    }

    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
