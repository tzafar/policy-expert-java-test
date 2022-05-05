package kata.supermarket.discount;

import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOneGetOneShould {
    public static final ItemByUnit snickers = new ItemByUnit(new Product(new BigDecimal("2.22")));
    public static final ItemByUnit mars = new ItemByUnit(new Product(new BigDecimal("2.00")));
    private final List<Item> items = new ArrayList<>();
    private final ProductDiscountRepository productDiscountRepository = new ProductDiscountRepository(new ArrayList<>());
    private final DiscountCalculator discountCalculator = new DiscountCalculator(productDiscountRepository);
    private final Basket basket = new Basket(discountCalculator);

    @AfterEach
    void setUp() {
      items.clear();
      productDiscountRepository.getProductDiscountsList().clear();
    }

    @Test
    @DisplayName("calculate correct total when a product with buy one get one free discount is added twice")
    public void totalWithDiscountAndTwoItemsAdded(){
        basket.add(snickers);
        basket.add(snickers);
        basket.add(mars);
        basket.add(mars);
        productDiscountRepository.addNew(new BuyOneGetOneFree(), new BigDecimal("2.22"));

        BigDecimal total = basket.total();

        assertEquals(total, new BigDecimal("6.22").setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("calculate correct total when a product with buy one get one free discount is added more than two times")
    public void totalWithDiscountAndMoreThanTwoItemsAdded(){
        basket.add(snickers);
        basket.add(snickers);
        basket.add(snickers);
        basket.add(snickers);
        productDiscountRepository.addNew(new BuyOneGetOneFree(), new BigDecimal("2.22"));

        BigDecimal total = basket.total();

        assertEquals(total, new BigDecimal("4.44").setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("calculate total as original price of the item if the product with buy one get one free is added only once")
    public void totalWithDiscountButOneItemAdded(){
        basket.add(new ItemByUnit(new Product(new BigDecimal("1.10"))));
        productDiscountRepository.addNew(new BuyOneGetOneFree(), new BigDecimal("1.10"));

        BigDecimal total = basket.total();

        assertEquals(total, new BigDecimal("1.10").setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("calculate total as sum of unit prices if a product doesn't have buy one get one free discount and is added twice")
    public void totalFromNoDiscounts(){
        basket.add(snickers);
        basket.add(snickers);

        BigDecimal total = basket.total();

        assertEquals(total, new BigDecimal("4.44").setScale(2, RoundingMode.HALF_UP));
    }
}
