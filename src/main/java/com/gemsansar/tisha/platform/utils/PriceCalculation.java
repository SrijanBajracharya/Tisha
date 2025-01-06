package com.gemsansar.tisha.platform.utils;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.order.domain.ItemType;
import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.stone.domain.Stone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceCalculation {

    public static BigDecimal orderTotal(Order order){
        List<Item> items = order.getItems();
        BigDecimal orderTotal = BigDecimal.valueOf(0);
        for(Item item : items){
            orderTotal = orderTotal.add(calculateItemTotal(item));
        }
        return orderTotal;
    }

    public static BigDecimal calculateItemTotal(Item item){
        BigDecimal stoneTotal = calculateTotalStonePrice(item.getStones());
        BigDecimal itemCost = calculateItemTotal(item.getRate(), item.getWeight());
        return itemCost.add(calculateMakingCharge(item)).add(stoneTotal);
    }

    private static BigDecimal calculateMakingCharge(Item item){
        BigDecimal jartiTotal = calculateTotalJarti(item.getCost().getJartiQuantity(), item.getRate());
        if(item.getItemType().equals(ItemType.SILVER)){
            return jartiTotal.multiply(item.getCost().getJyala());
        }
        return jartiTotal.add(item.getCost().getJyala());
    }

    private static BigDecimal calculateItemTotal(BigDecimal rate, Double itemWeight){
        return rate.multiply(BigDecimal.valueOf(itemWeight));
    }

    private static BigDecimal calculateTotalJarti(Double jartiQuantity, BigDecimal rate){
        return rate.multiply(BigDecimal.valueOf(jartiQuantity));
    }

    private static BigDecimal calculateTotalStonePrice(List<Stone> stones){
        if(stones == null){
            return BigDecimal.valueOf(0);
        }
        BigDecimal total = BigDecimal.valueOf(0);
        for(Stone stone : stones){
            BigDecimal price = calculateStonePrice(stone);
            total = total.add(price);
        }
        return total;
    }

    private static BigDecimal calculateStonePrice(Stone stone){
        return stone.getPrice().multiply(BigDecimal.valueOf(stone.getQuantity()));
    }
}
