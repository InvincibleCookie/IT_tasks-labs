package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class GoodsTransporter { // 9 вариант
    public static AtomicInteger totalWeight = new AtomicInteger(0);
    public static final int maxWeight = 150;
    private static final int numberOfWorkers = 3;
    static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();

        warehouse1.addGood(new Good("Товар 1", 20));
        warehouse1.addGood(new Good("Товар 2", 50));
        warehouse1.addGood(new Good("Товар 3", 75));
        warehouse1.addGood(new Good("Товар 4", 40));
        warehouse1.addGood(new Good("Товар 5", 80));
        warehouse1.addGood(new Good("Товар 6", 60));
        warehouse1.addGood(new Good("Товар 7", 15));
        warehouse1.addGood(new Good("Товар 8", 150));
        warehouse1.addGood(new Good("Товар 9", 45));


        // Не известно почему, но после изменения веса некоторых товаров
        // код работает исправно и сам завершает работу

//        warehouse1.addGood(new Good("Товар 1", 25));
//        warehouse1.addGood(new Good("Товар 2", 50));
//        warehouse1.addGood(new Good("Товар 3", 150));
//        warehouse1.addGood(new Good("Товар 4", 45));
//        warehouse1.addGood(new Good("Товар 5", 30));
//        warehouse1.addGood(new Good("Товар 6", 80));
//        warehouse1.addGood(new Good("Товар 7", 10));
//        warehouse1.addGood(new Good("Товар 8", 100));
//        warehouse1.addGood(new Good("Товар 9", 70));

        cyclicBarrier = new CyclicBarrier(numberOfWorkers);
        List<Thread> workers = new ArrayList<>();

        while (!warehouse1.getGoods().isEmpty()) {
            System.out.println("Склад 1: " + warehouse1.getGoods());
            System.out.println("Склад 2: " + warehouse2.getGoods());


            workers = new ArrayList<>();

            for (Good good : warehouse1.getGoods()) {
                if (totalWeight.get() >= 150) {

                    break;
                }
                workers.add(new Thread(new Transportation(warehouse1, warehouse2, good)));
            }

            for (Thread worker : workers) {
                worker.start();
            }

            // Дадим потокам достаточно времени для завершения перед следующей итерацией
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e);
            }

            System.out.println("Общий вес перенесенных товаров: " + totalWeight.get() + " кг");
            totalWeight = new AtomicInteger(0);
        }
        System.out.println("Всё");
        for (Thread worker : workers) {
            try {
                worker.join();
            }
            catch (Exception ex) {
                return;
            }
        }
        System.out.println(workers.size());
    }
}

class Good {
    private String name;
    private int weight;

    public Good(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Good{name='" + name + "', weight=" + weight + '}';
    }
}


class Warehouse {
    private List<Good> goods;

    public Warehouse() {
        this.goods = new ArrayList<>();
    }

    public void addGood(Good good) {
        goods.add(good);
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void removeGood(Good good) {
        this.goods.remove(good);
    }
}

class Transportation implements Runnable {
    private Warehouse warehouse1;
    private Warehouse warehouse2;
    private Good good;

    public Transportation(Warehouse warehouse1, Warehouse warehouse2, Good good) {
        this.warehouse1 = warehouse1;
        this.warehouse2 = warehouse2;
        this.good = good;
    }

    @Override
    public void run() {
        try {
            if (GoodsTransporter.totalWeight.addAndGet(good.getWeight()) <= GoodsTransporter.maxWeight) {
                warehouse2.addGood(good);
                warehouse1.removeGood(good);
                System.out.println("Перенос товара весом: " + good.getWeight() + " кг");
            } else {
                GoodsTransporter.totalWeight.addAndGet(-good.getWeight());
            }
            GoodsTransporter.cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Error: " + e);
        }
    }
}
