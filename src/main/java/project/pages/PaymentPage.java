package project.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class PaymentPage extends BasePage {
    
    private By nameField = AppiumBy.accessibilityId("Full Name* input field");
    private By cardNumberField = AppiumBy.accessibilityId("Card Number* input field");
    private By expDateField = AppiumBy.accessibilityId("Expiration Date* input field");
    private By cvvField = AppiumBy.accessibilityId("Security Code* input field");
    private By reviewOrderButton = AppiumBy.accessibilityId("Review Order button");
    private By errorInvalidValue = AppiumBy
            .iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Value looks invalid.\"`][2]");
    private By pageDesc = AppiumBy.accessibilityId("Enter a payment method");

    public PaymentPage(IOSDriver driver) {
        super(driver);
    }

    public void enterPaymentDetails(String name, String cardNumber, String expDate, String cvv) {
        safeType(nameField, name);
        safeType(cardNumberField, cardNumber);
        safeType(cvvField, cvv);
        safeType(expDateField, expDate);
        hideKeyboard();
    }

    public String getErrorInvalidValue() {
        return grabText(errorInvalidValue);
    }

    public String getPageDesc() {
        return grabText(pageDesc);
    }
    
    public void clickReviewOrder() {
        safeClick(reviewOrderButton);
        safeClick(reviewOrderButton);
    }

}
