package kata.supermarket.discount;

import kata.supermarket.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DiscountCalculatorShould {
    private final ProductDiscountRepository productDiscountRepository = Mockito.mock(ProductDiscountRepository.class);
    private final DiscountCalculator discountCalculator = new DiscountCalculator(productDiscountRepository);
    ProductDiscount productDiscount = new ProductDiscount(new BuyOneGetOneFree(), Collections.singletonList(new BigDecimal("2.22")));

    @Test
    @DisplayName("calculate correct discount when a product with buy one get one free discount is added twice")
    public void discountWhenTwoItemsAdded() {
        BigDecimal expected = new BigDecimal("2.22").setScale(2, RoundingMode.HALF_UP);

        when(productDiscountRepository.getProductDiscountsList()).thenReturn(Collections.singletonList(productDiscount));

        Product product = new Product(new BigDecimal("2.22"));
        BigDecimal actual = discountCalculator.calculate(Arrays.asList(product.oneOf(), product.oneOf()));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calculate correct discount when a product with buy one get one free discount is added more than two times")
    public void discountWhenMoreThanTwoItemsAdded() {
        BigDecimal expected = new BigDecimal("2.22").setScale(2, RoundingMode.HALF_UP);

        when(productDiscountRepository.getProductDiscountsList()).thenReturn(Collections.singletonList(productDiscount));

        Product product = new Product(new BigDecimal("2.22"));
        Product product2 = new Product(new BigDecimal("1.10"));
        BigDecimal actual = discountCalculator.calculate(Arrays.asList(product.oneOf(), product.oneOf(), product.oneOf(), product2.oneOf()));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calculate discount as zero if the product with buy one get one free is added only once")
    public void discountWhenOnlyOneItemIsAdded() {
        BigDecimal expected = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        when(productDiscountRepository.getProductDiscountsList()).thenReturn(Collections.singletonList(productDiscount));

        Product product = new Product(new BigDecimal("2.22"));
        BigDecimal actual = discountCalculator.calculate(Collections.singletonList(product.oneOf()));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calculate discount as zero if a product doesn't have buy one get one free discount and is added twice")
    public void totalFromNoDiscounts() {
        BigDecimal expected = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        when(productDiscountRepository.getProductDiscountsList()).thenReturn(Collections.emptyList());

        Product product = new Product(new BigDecimal("2.22"));
        BigDecimal actual = discountCalculator.calculate(Arrays.asList(product.oneOf(), product.oneOf()));

        assertEquals(expected, actual);
    }
}
