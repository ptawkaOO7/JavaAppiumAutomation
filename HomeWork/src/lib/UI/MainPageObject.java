package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 10);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void longTap(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int x = element.getLocation().getX();
        int y = element.getLocation().getY();

        TouchAction action = new TouchAction(driver);
        action
                .longPress(element)
                .waitAction(150)
                .release()
                .perform();
    }

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by, error_message, 10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public void assertElementPresent(By by, String error_message)
    {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements < 1) {
            String default_message = "An element '" + by.toString() + "' should be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public WebElement assertElementHasText(By by, String expected_placeholder_text, String error_message)
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

    public WebElement assertElementContainText(By by, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message);
        String item_title = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                item_title.toLowerCase().contains(expected_text)
        );
        return element;
    }

    public List<WebElement> waitForALLElementsPresent(By by, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage("No results found\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public List<WebElement> assertAllElementsContainText(By by, String expected_text, long timeoutSeconds)
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
