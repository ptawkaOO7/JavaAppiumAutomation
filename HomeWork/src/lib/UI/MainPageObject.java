package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 10);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void longTap(String locator, String error_message)
    {
        WebElement element = waitForElementPresent(locator, error_message, 10);

        int x = element.getLocation().getX();
        int y = element.getLocation().getY();

        TouchAction action = new TouchAction(driver);
        action
                .longPress(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .release()
                .perform();
    }

    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeElementToLeft(String locator, String error_message)
    {
        WebElement element = waitForElementPresent(
                locator, error_message, 10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }

    public void assertElementPresent(String locator, String error_message)
    {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements < 1) {
            String default_message = "An element '" + locator.toString() + "' should be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public WebElement assertElementHasText(String locator, String expected_placeholder_text, String error_message)
    {
        WebElement element = waitForElementPresent(locator, error_message);
        String placeholder_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_placeholder_text,
                placeholder_text
        );
        return element;
    }

    public WebElement assertElementContainText(String locator, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(locator, error_message);
        String item_title = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                item_title.toLowerCase().contains(expected_text)
        );
        return element;
    }

    public List<WebElement> waitForALLElementsPresent(String locator, long timeoutSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage("No results found\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public List<WebElement> assertAllElementsContainText(String locator, String expected_text, long timeoutSeconds)
    {
        List<WebElement> elements = waitForALLElementsPresent(locator, timeoutSeconds);
        for(WebElement element : elements){
            String item_title = element.getAttribute("text");
            Assert.assertTrue(
                    "String " + item_title + " doesn't contain " + expected_text,
                    item_title.toLowerCase().contains(expected_text)
            );
        }
        return elements;
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}
