package project.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class CatalogPage extends BasePage {
    
    private By cartMenu = AppiumBy.accessibilityId("tab bar option cart");
    private By sortButton = AppiumBy.accessibilityId("sort button");
    private By sortPriceAsc = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Price - Ascending'`]");
    private By sortNameDesc = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Name - Descending'`]");
    private By productNames = AppiumBy.iOSNsPredicateString("name == 'store item text'");
    private By productPrices = AppiumBy.iOSNsPredicateString("name == 'store item price'");

    public CatalogPage(IOSDriver driver) {
        super(driver);
    }

    public void clickProduct(String product) {
        safeClick(AppiumBy.iOSNsPredicateString("name == 'store item text' AND label == '" + product + "'"));
    }

    public void sortByPriceAscending() {
        safeClick(sortButton);
        safeClick(sortPriceAsc);
    }

    public void sortByNameDescending() {
        safeClick(sortButton);
        safeClick(sortNameDesc);
    }

    public List<WebElement> getActualProductByName() {
        return elementsPresence(productNames);
    }


    public List<WebElement> getActualProductByPrice() {
        return elementsPresence(productPrices);
    }

    public void openCartPage() {
        safeClick(cartMenu);
    }
}
