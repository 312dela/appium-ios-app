package project.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public abstract class BasePage {
    
    protected IOSDriver driver;
    private WebDriverWait wait;

    protected BasePage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    protected void safeClick(By locator) {
        getElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String grabText(By locator) {
        return getElement(locator).getText();
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> elementsPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void safeType(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    protected void hideKeyboard() {
        driver.hideKeyboard("Return");
    }
}
