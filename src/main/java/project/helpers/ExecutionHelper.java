package project.helpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import project.pages.PageObjectManager;

public class ExecutionHelper {

    private PageObjectManager page;

    public ExecutionHelper(PageObjectManager page) {

        this.page = page;

    }

    public double getActualTotalPrice() {
        String actualPriceText = page.getCartPage().getTotalPrice();
        String actualTotal = actualPriceText.split("\\$")[1];
        return Double.parseDouble(actualTotal);
    }

    public double calculateTotalPrice() {
        List<WebElement> allProductPrices = page.getCartPage().getAllProductPrices();
        double expectedTotal = 0;
        for (WebElement priceElement : allProductPrices) {
            String priceText = priceElement.getText();
            String priceValue = priceText.split("\\$")[1];
            double price = Double.parseDouble(priceValue);
            expectedTotal += price;
        }
        return expectedTotal;
    }

    public void addProductToCart(String product1, String product2) {
        page.getCatalogPage().clickProduct(product1);
        page.getCartPage().addToCart();
        page.getCartPage().clickBack();
        page.getCatalogPage().clickProduct(product2);
        page.getCartPage().addToCart();
        page.getCatalogPage().openCartPage();
    }

    public void checkoutAndLogin(String username, String password) {
        page.getCartPage().clickCheckout();
        page.getLoginPage().enterLoginForm(username, password);
    }

    public void fillCheckoutDetails() {
        page.getCheckoutPage().enterCheckoutDetails(Loader.account().name(), Loader.address().addressLine1(),
                Loader.address().addressLine2(), Loader.address().city(), Loader.address().state(),
                Loader.address().postalCode(), Loader.address().country());
    }

    public void fillPaymentDetails(String expDate) {
        page.getPaymentPage().enterPaymentDetails(Loader.account().name(), Loader.payment().cardNumber(), expDate,
                Loader.payment().cvv());
    }

    public List<String> getActualProductByName() {
        List<WebElement> productElements = page.getCatalogPage().getActualProductByName();
        return productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> expectedSortByNameDesc(List<String> actualProductByName) {
        return actualProductByName.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Double> getActualProductByPrice() {
        List<WebElement> productPriceElements = page.getCatalogPage().getActualProductByPrice();
        return productPriceElements.stream()
                .map(WebElement::getText)
                .map(s -> s.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public List<Double> expectedSortByPriceAsc(List<Double> actualProductByPrice) {
        return actualProductByPrice.stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
