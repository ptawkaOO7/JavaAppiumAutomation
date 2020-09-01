package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
