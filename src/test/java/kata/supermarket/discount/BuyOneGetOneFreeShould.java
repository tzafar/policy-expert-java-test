package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreeShould {
    private final BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree();

    @MethodSource
    @ParameterizedTest(name = "{0}")
    void buyOneGetOneFreeDiscountCalculation(String description, List<Item> items, List<BigDecimal> itemsWithDiscounts, BigDecimal expected) {
        BigDecimal actual = buyOneGetOneFree.apply(items, itemsWithDiscounts);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> buyOneGetOneFreeDiscountCalculation() {
        return Stream.of(
                Arguments.of("apply discount correct when there are items in basket with applicable discount",
                        itemsInBasket(),
                        itemsWithDiscount(),
                        new BigDecimal("2.22")),
                Arguments.of("apply discount correct when there are NO items in basket with applicable discount",
                        itemsInBasketWithNoDiscount(),
                        itemsWithDiscount(),
                        BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)));
    }

    private static List<Item> itemsInBasket() {
        return Arrays.asList(new Product(new BigDecimal("2.22")).oneOf(), new Product(new BigDecimal("2.22")).oneOf());
    }

    private static List<Item> itemsInBasketWithNoDiscount() {
        return Arrays.asList(new Product(new BigDecimal("3.12")).oneOf(), new Product(new BigDecimal("2.22")).oneOf());
    }

    private static List<BigDecimal> itemsWithDiscount() {
        return Collections.singletonList(new BigDecimal("2.22"));
    }

}
