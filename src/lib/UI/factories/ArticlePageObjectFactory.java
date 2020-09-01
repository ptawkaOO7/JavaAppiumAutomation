package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.android.AndriodArticlePageObject;
import lib.UI.ios.iOSArticlePageObject;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndriodArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
