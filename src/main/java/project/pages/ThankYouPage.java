package project.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class ThankYouPage extends BasePage {
    
    private By thankYouMessage = AppiumBy.accessibilityId("Thank you for your order");

    public ThankYouPage(IOSDriver driver) {
        super(driver);
    }

    public String getThankYouMessage() {
        return grabText(thankYouMessage);
    }

}
