package project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class CartPage extends BasePage {
    
    private By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");
    private By productPrice = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'product price'`]");
    private By totalPrice = AppiumBy.accessibilityId("total price");
    private By checkoutButton = AppiumBy.accessibilityId("Proceed To Checkout button");
    private By backButton = AppiumBy.accessibilityId("navigation back button");

    public CartPage(IOSDriver driver) {
        super(driver);
    }

    public void addToCart() {
        safeClick(addToCartButton);
    }

    public List<WebElement> getAllProductPrices() {
        return elementsPresence(productPrice);
    }

    public String getTotalPrice() {
        return grabText(totalPrice);
    }

    public void clickCheckout() {
        safeClick(checkoutButton);
    }

    public void clickBack() {
        safeClick(backButton);
    }
}
