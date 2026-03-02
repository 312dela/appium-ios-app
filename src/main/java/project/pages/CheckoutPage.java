package project.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class CheckoutPage extends BasePage {

    private By nameField = AppiumBy.accessibilityId("Full Name* input field");
    private By addressField1 = AppiumBy.accessibilityId("Address Line 1* input field");
    private By addressField2 = AppiumBy.accessibilityId("Address Line 2 input field");
    private By cityField = AppiumBy.accessibilityId("City* input field");
    private By stateField = AppiumBy.accessibilityId("State/Region input field");
    private By zipCodeField = AppiumBy.accessibilityId("Zip Code* input field");
    private By countryField = AppiumBy.accessibilityId("Country* input field");
    private By paymentButton = AppiumBy.accessibilityId("To Payment button");

    public CheckoutPage(IOSDriver driver) {
        super(driver);
    }

    public void enterCheckoutDetails(String name, String address1, String address2, String city, String state, String postalCode, String country) {
        safeType(nameField, name);
        safeType(addressField1, address1);
        safeType(addressField2, address2);
        safeType(cityField, city);
        safeType(stateField, state);
        hideKeyboard();
        safeType(zipCodeField, postalCode);
        hideKeyboard();
        safeType(countryField, country);
        hideKeyboard();
        safeClick(paymentButton);
    }
}