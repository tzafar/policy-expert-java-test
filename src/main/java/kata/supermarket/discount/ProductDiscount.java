package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.List;

public class ProductDiscount {
    private Discount discount;
    private List<BigDecimal> products;

    public ProductDiscount(Discount discount, List<BigDecimal> products) {
        this.discount = discount;
        this.products = products;
    }

    public Discount getDiscount() {
        return discount;
    }

    public List<BigDecimal> getProducts() {
        return products;
    }
}
