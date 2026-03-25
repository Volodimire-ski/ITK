package StreamAPI;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumbersGeneration {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        Map<String,List<Order>> groupedByProducts = orders.stream().collect(Collectors.groupingBy(Order::getProduct));
        System.out.println("Группировка orders по полю product: "+groupedByProducts);

        Map<String,Double> summedCosts = orders.stream().collect(Collectors.groupingBy(Order::getProduct,Collectors.summingDouble(Order::getCost)));
        System.out.println("Общая стоимость каждого товара: "+summedCosts);

        List<Map.Entry<String,Double>> orderedByTotalCost = summedCosts.entrySet().stream().sorted((a,b)->b.getValue().compareTo(a.getValue())).toList();
        System.out.println("Товары, отсортированные по общей стоимости: "+orderedByTotalCost);

        List<Order> threeMostExpProducts = orders.stream().sorted((o1,o2)-> {
            Double a = o1.getCost();
            Double b = o2.getCost();
            return b.compareTo(a);
                }).limit(3).toList();
        System.out.println("3 самых дорогих товара: "+threeMostExpProducts);

        Map<String,Double> totalCostOf3ExpProd = threeMostExpProducts.stream().collect(Collectors.toMap(Collectors::joining,Collectors.summingDouble(Order::getCost)));
        // this method doesn't work, I can't join products' names to List or to String.
    }
}
