package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        PLACEHOLDER_TEXT = "id:Close";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:swipe action save";
        ITEM_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        FIRST_ITEM_TITLE = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][1]//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        BACK_BUTTON = "id:Close";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
