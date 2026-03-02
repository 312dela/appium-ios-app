package project.pages;

import io.appium.java_client.ios.IOSDriver;

public class PageObjectManager {
    
    private IOSDriver driver;
    private LoginPage loginPage;
    private CatalogPage catalogPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private ReviewOrderPage reviewOrderPage;
    private ThankYouPage thankYouPage;

    public PageObjectManager(IOSDriver driver) {
        this.driver = driver;
    }

    public CatalogPage getCatalogPage() {

        if (catalogPage == null) {
            catalogPage = new CatalogPage(driver);
        }

        return catalogPage;

    }

    public CartPage getCartPage() {

        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }

        return cartPage;

    }

    public CheckoutPage getCheckoutPage() {

        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver);
        }

        return checkoutPage;

    }

    public ThankYouPage getThankYouPage() {

        if (thankYouPage == null) {
            thankYouPage = new ThankYouPage(driver);
        }

        return thankYouPage;

    }

    public PaymentPage getPaymentPage() {

        if (paymentPage == null) {
            paymentPage = new PaymentPage(driver);
        }

        return paymentPage;

    }

    public ReviewOrderPage getReviewOrderPage() {

        if (reviewOrderPage == null) {
            reviewOrderPage = new ReviewOrderPage(driver);
        }

        return reviewOrderPage;

    }

    public LoginPage getLoginPage() {

        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }

        return loginPage;

    }

}
