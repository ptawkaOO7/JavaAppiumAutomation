import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/ptawka007/Documents/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testComparePlaceholderText()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input"
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Cannot find placeholder 'Search…' in the search toolbar"
        );
    }

    @Test
    public void testSearchArticlesAndCancel()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "selenium",
                "Cannot find search input"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "No results found"
        );

        assertElementContainText(
                By.xpath(
                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][2]//*[@resource-id='org.wikipedia:id/page_list_item_title']"
                ),
                "selenium",
                "Cannot find 'selenium' in the search results"
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field"
        );

        waitForElementNotPresent(
                By.id("resource-id='org.wikipedia:id/search_results_list"),
                "Search results are still present on the page",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search"
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareSearchResults()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "selenium",
                "Cannot find search input"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "No results found"
        );

        assertAllElementsContainText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "selenium",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        element.sendKeys(value);
        return element;
    }

    private WebElement assertElementHasText(By by, String expected_placeholder_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        String placeholder_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_placeholder_text,
                placeholder_text
         );
        return element;
    }

    private WebElement assertElementContainText(By by, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        String item_title = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                item_title.toLowerCase().contains(expected_text)
        );
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        element.clear();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private List<WebElement> waitForALLElementsPresent(By by, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage("No results found\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private List<WebElement> assertAllElementsContainText(By by, String expected_text, long timeoutSeconds)
    {
        List<WebElement> elements = waitForALLElementsPresent(by, timeoutSeconds);
        for(WebElement element : elements){
            String item_title = element.getAttribute("text");
            Assert.assertTrue(
                    "String " + item_title + " doesn't contain " + expected_text,
                    item_title.toLowerCase().contains(expected_text)
            );
        }
        return elements;
    }
}
