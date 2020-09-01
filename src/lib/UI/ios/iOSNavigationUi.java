package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUi;

public class iOSNavigationUi extends NavigationUi {

    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public iOSNavigationUi(AppiumDriver driver)
    {
        super(driver);
    }
}
