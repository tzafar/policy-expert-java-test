package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DiscountCalculator {
    private final ProductDiscountRepository productDiscountRepository;

    public DiscountCalculator(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    public BigDecimal calculate(List<Item> items) {
        List<ProductDiscount> productDiscounts = productDiscountRepository.getProductDiscountsList();
        return productDiscounts.stream().
                map(p -> p.getDiscount().apply(items, p.getProducts()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }
}
