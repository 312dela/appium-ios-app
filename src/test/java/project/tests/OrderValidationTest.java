package project.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import io.qameta.allure.Allure;
import project.helpers.Loader;
import project.pages.PaymentPage;

@Tag("order-flow")
@DisplayName("Order Validation Test")
public class OrderValidationTest extends BaseTest {

    @Tag("user")
    @Nested
    @DisplayName("User Validation in Order Creation")
    class UserValidationInOrderFlow {
        private String product1;
        private String product2;
        private String password;

        @BeforeEach
        public void initUserValidationInOrderFlow() {
            Allure.label("parentSuite", "Order Validation Test");
            Allure.label("suite", "User Validation in Order Creation");
            product1 = Loader.product().product1();
            product2 = Loader.product().product2();
            password = Loader.account().password();
        }

        @Tag("regression")
        @Test
        @DisplayName("Create order using locked out username")
        public void createOrderUsingLockedOutUsername() {
            String locked_out_username = Loader.account().lockedOutUsername();

            getExecution().addProductToCart(product1, product2);

            getExecution().checkoutAndLogin(locked_out_username, password);

            String actualMessage = getPage().getLoginPage().getErrorLockedOut();
            String expectedMessage = "Sorry, this user has been locked out.";

            getAssertion().assertDataEquals(expectedMessage, actualMessage);
        }

        @ParameterizedTest(name = "- {0}")
        @DisplayName("Create order using active username in different variants")
        @MethodSource("provideUsernames")
        public void createOrderUsingActiveUsername(String username) {
            String activeCard = Loader.payment().validCard();

            getExecution().addProductToCart(product1, product2);
            getExecution().checkoutAndLogin(username, password);

            try {
                getExecution().fillCheckoutDetails();
            } catch (Exception e) {
                throw new AssertionError(e);
            }

            getExecution().fillPaymentDetails(activeCard);
            getPage().getPaymentPage().clickReviewOrder();
            getPage().getReviewOrderPage().clickPlaceOrderButton();

            String actualThankYouMessage = getPage().getThankYouPage().getThankYouMessage();
            String expectedThankYouMessage = "Thank you for your order";

            assertEquals(expectedThankYouMessage, actualThankYouMessage,
                    "Thank you message mismatch for username: " + username);
        }

        private static Stream<Arguments> provideUsernames() {
            String baseUsername = Loader.account().activeUsername();
            return Stream.of(
                    Arguments.of(baseUsername),
                    Arguments.of(baseUsername.toUpperCase()));
        }
    }

    @Tag("rule")
    @Nested
    @DisplayName("Rule Validation in Order Flow")
    class RuleValidationInOrderFlow {
        private String product1;
        private String product2;

        @BeforeEach
        public void initTestData() {
            Allure.label("parentSuite", "Order Validation Test");
            Allure.label("suite", "Rule Validation in Order Flow");
            product1 = Loader.product().product1();
            product2 = Loader.product().product2();
        }

        @Tag("regression")
        @Tag("smoke")
        @Test
        @DisplayName("Calculate the total price of the products in the cart")
        public void calculateTotalPriceInCart() {
            getExecution().addProductToCart(product1, product2);
            Allure.step("Products used: " + product1 + " and " + product2);
            double expectedTotalPrice = getExecution().calculateTotalPrice();
            double actualTotalPrice = getExecution().getActualTotalPrice();

            getAssertion().assertDataEquals(expectedTotalPrice, actualTotalPrice);
        }

        @Tag("regression")
        @Test
        @DisplayName("Use invalid card for payment method")
        public void useInvalidCard() {
            String active_username = Loader.account().activeUsername();
            String password = Loader.account().password();
            String expireCard = Loader.payment().invalidCard();

            PaymentPage paymentPage = getPage().getPaymentPage();

            getExecution().addProductToCart(product1, product2);
            getExecution().checkoutAndLogin(active_username, password);
            getExecution().fillCheckoutDetails();
            getExecution().fillPaymentDetails(expireCard);

            String actualMessage = paymentPage.getErrorInvalidValue();
            String expectedMessage = "Value looks invalid.";

            getAssertion().assertDataEquals(expectedMessage, actualMessage);

            paymentPage.clickReviewOrder();

            try {
                String actualTitle = paymentPage.getPageDesc();
                String expectedTitle = "Enter a payment method";

                getAssertion().assertDataEquals(expectedTitle, actualTitle);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        }
    }
}
