package lab2;

public class Dishwasher extends Appliance {
    private int capacity;

    public Dishwasher(String brand, int voltage, int amperage, int capacity) {
        super(brand, voltage, amperage);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void washDishes() {
        System.out.println("Запуск посудомоечной машины без программы.");
    }

    public void washDishes(String program) {
        System.out.println("Запуск посудомоечной машины с программой: " + program);
    }
}