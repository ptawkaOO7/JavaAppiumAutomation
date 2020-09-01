package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUi;

public class AndroidNavigationUi extends NavigationUi {

    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUi(AppiumDriver driver)
    {
        super(driver);
    }
}
