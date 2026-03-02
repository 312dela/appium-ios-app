package project.tests;

import java.util.List;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("sorting-flow")
@Tag("regression")
@DisplayName("Sorting Validation Test")
public class SortingValidationTest extends BaseTest {


    @Test
    @DisplayName("Sort products by name in descending order")
    public void sortProductNameDescending() {
        getPage().getCatalogPage().sortByNameDescending();

        List<String> actualProductByName = getExecution().getActualProductByName();
        List<String> expectedProductByName = getExecution().expectedSortByNameDesc(actualProductByName);

        getAssertion().assertDataEquals(expectedProductByName, actualProductByName);
    }

    @Tag("smoke")
    @Test
    @DisplayName("Sort products by price in ascending order")
    public void sortProductPriceAscending() {
        getPage().getCatalogPage().sortByPriceAscending();

        List<Double> actualProductByPrice = getExecution().getActualProductByPrice();
        List<Double> expectedProductByPrice = getExecution().expectedSortByPriceAsc(actualProductByPrice);

        getAssertion().assertDataEquals(expectedProductByPrice, actualProductByPrice);
    }
}
