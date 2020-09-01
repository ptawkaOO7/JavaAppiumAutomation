package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
        PLACEHOLDER_TEXT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        ITEM_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        FIRST_ITEM_TITLE = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][1]//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@class = 'android.widget.RelativeLayout']//*[contains(@text, 'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        MY_LIST_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        BACK_BUTTON = "xpath://*[@class='android.widget.ImageButton']";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
