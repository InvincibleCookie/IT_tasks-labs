package lab6;
import java.util.*;

public class SalesTracker {
    // TreeMap для хранения продаж (название продукта - количество)
    private TreeMap<String, Integer> sales;

    // TreeMap для хранения цен (название продукта - цена)
    private TreeMap<String, Double> prices;

    // Конструктор класса
    public SalesTracker() {
        // Инициализация TreeMap для продаж
        this.sales = new TreeMap<>();

        // Инициализация TreeMap для цен
        this.prices = new TreeMap<>();
    }

    // Метод для добавления продажи
    public void addSale(String name, double price, int quantity) {
        // Положить в TreeMap цен (prices), если продукта там еще нет
        prices.putIfAbsent(name, price);

        // Обновить или добавить продажу в TreeMap продаж (sales)
        sales.put(name, sales.getOrDefault(name, 0) + quantity);
    }

    // Метод для вывода информации о продажах
    public void displaySales() {
        System.out.println("Список проданных товаров:");
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = prices.get(product);
            System.out.println(product + " - Количество: " + quantity + " - Общая стоимость: $" + (price * quantity));
        }
    }

    // Метод для расчета общей суммы продаж
    public double calculateTotalSales() {
        double totalSales = 0;
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = prices.get(product);
            totalSales += price * quantity;
        }
        return totalSales;
    }

    // Метод для получения наиболее популярного товара
    public String getMostPopularProduct() {
        if (sales.isEmpty()) {
            return null;
        }

        // Находим товар с максимальным количеством продаж
        Map.Entry<String, Integer> maxEntry = Collections.max(
                sales.entrySet(), Comparator.comparingInt(Map.Entry::getValue)
        );

        return maxEntry.getKey();
    }


    public static void main(String[] args) {
        SalesTracker salesTracker = new SalesTracker();

        salesTracker.addSale("Ноутбук", 999.99, 5);
        salesTracker.addSale("Смартфон", 500, 10);
        salesTracker.addSale("Планшет", 300, 3);

        salesTracker.displaySales();
        System.out.println("Общая сумма продаж: $" + salesTracker.calculateTotalSales());
        System.out.println("Самый популярный товар: " + salesTracker.getMostPopularProduct());


    }
}
