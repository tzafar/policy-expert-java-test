package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductDiscountRepository {
    private List<ProductDiscount> productDiscounts;

    public ProductDiscountRepository(List<ProductDiscount> productDiscount) {
        this.productDiscounts = productDiscount;
    }

    public List<ProductDiscount> getProductDiscountsList() {
        return productDiscounts;
    }

    public void addNew(Discount discount, BigDecimal product) {
        ProductDiscount productDiscount = this.productDiscounts.stream().filter(pd -> pd.getDiscount().getClass().equals(discount.getClass())).findFirst().orElse(null);
        if(productDiscount == null){
            this.productDiscounts.add(new ProductDiscount(discount, Arrays.asList(product)));
        } else {
            productDiscount.getProducts().add(product);
        }
    }
}
