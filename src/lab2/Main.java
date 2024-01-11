package lab2;

public class Main {
    public static void main(String[] args) {
        Fridge fridge = new Fridge("Samsung", 220, 5, 300);

        System.out.println("Бренд холодильника: " + fridge.getBrand());
        System.out.println("Напряжение: " + fridge.getVoltage() + " Вольт");
        System.out.println("Ампераж: " + fridge.getAmperage() + " Ампер");
        System.out.println("Вместимость: " + fridge.getCapacity());

        fridge.setCapacity(400);
        System.out.println("Новая вместимость: " + fridge.getCapacity());

        fridge.addProduct("Яблоко");
        fridge.addProduct("Молоко");
        fridge.addProduct("Сок");

        fridge.listProducts();

        fridge.removeProduct("Молоко");
        fridge.removeProduct("Хлеб");

        fridge.listProducts();

        Dishwasher dishwasher = new Dishwasher("Bosch", 220, 10, 12);
        dishwasher.washDishes();
        dishwasher.washDishes("Интенсив");

        VacuumCleaner vacuumCleaner = new VacuumCleaner("Dyson", 220, 5, 1000);
        VacuumCleaner vacuumCleaner2 = new VacuumCleaner("Xiaomi", 220, 1, 1500);
        System.out.println("Мощность: " + vacuumCleaner2.power() + " Вт");
        System.out.println("Количество созданных объектов класса VacuumCleaner: " + VacuumCleaner.getObjectCount());

        Fridge2 fr = new Fridge2("Samsung", 220, 5, 300, 300);
        fr.listProducts();
        System.out.println(fr.capacity2);

    }
}
