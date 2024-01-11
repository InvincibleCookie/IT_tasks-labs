package lab2;
import java.util.ArrayList;
import java.util.List;
public class Fridge extends Appliance {
    private int capacity;
    private final List<String> products;

    public Fridge(String brand, int voltage, int amperage, int capacity) {
        super(brand, voltage, amperage);
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void addProduct(String product) {
        if (products.size() < capacity) {
            products.add(product);
            System.out.println("Продукт '" + product + "' добавлен в холодильник.");
        } else {
            System.out.println("Продукт '" + product + "' не может быть добавлен.");
        }
    }

    public void removeProduct(String product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Продукт '" + product + "' удален из холодильника.");
        } else {
            System.out.println("Продукт '" + product + "' не найден в холодильнике.");
        }
    }

    public void listProducts() {
        System.out.println("Продукты в холодильнике:");
        for (String product : products) {
            System.out.println(product);
        }
    }
}