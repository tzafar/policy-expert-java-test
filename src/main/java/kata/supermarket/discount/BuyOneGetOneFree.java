package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuyOneGetOneFree implements Discount {

    @Override
    public BigDecimal apply(List<Item> items, List<BigDecimal> products) {
        Map<BigDecimal, Long> priceToCountMap = items.stream().collect(Collectors.groupingBy(Item::price, Collectors.counting()));
        BigDecimal discount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (BigDecimal key: priceToCountMap.keySet()) {
            if (priceToCountMap.get(key) > 1 && products.contains(key)){
                double discountFactor = Math.floor(priceToCountMap.get(key) / 2);
                discount = discount.add(key.multiply(new BigDecimal(discountFactor)));
            };
        }
        return discount.setScale(2, RoundingMode.HALF_UP);
    }
}
