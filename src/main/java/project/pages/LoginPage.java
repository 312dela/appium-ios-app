package project.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class LoginPage extends BasePage {
    
    private By usernameField = AppiumBy.accessibilityId("Username input field");
    private By passwordField = AppiumBy.accessibilityId("Password input field");
    private By loginButton = AppiumBy.accessibilityId("Login button");
    private By errorLockedOut= AppiumBy.accessibilityId("Sorry, this user has been locked out.");

    public LoginPage(IOSDriver driver) {
        super(driver);
    }

    public void enterLoginForm(String username, String password) {
        safeType(usernameField, username);
        safeType(passwordField, password);
        hideKeyboard();
        safeClick(loginButton);
    }

    public String getErrorLockedOut() {        
        return grabText(errorLockedOut);
    }
}
