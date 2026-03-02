package project.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class ReviewOrderPage extends BasePage {
    
    private By placeOrderButton = AppiumBy.accessibilityId("Place Order button");

    public ReviewOrderPage(IOSDriver driver) {
        super(driver);
    }

    public void clickPlaceOrderButton() {
        safeClick(placeOrderButton);
    }
    
}
