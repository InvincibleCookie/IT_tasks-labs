package lab7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WarehouseTransfer {
    private static final AtomicInteger totalWeight = new AtomicInteger(0);
    private static final int WEIGHT_LIMIT = 150;

    public static void main(String[] args) throws Exception {
        List<Integer> goods = new ArrayList<>(List.of(30, 20, 50, 10, 40, 60, 30, 50));

        ForkJoinPool pool = new ForkJoinPool(3); // Пул с тремя грузчиками (потоками)

        // Список для хранения Future объектов, которые представляют результат выполнения задач
        List<Future<?>> futures = new ArrayList<>();

        for (int weight : goods) {
            if (totalWeight.get() >= WEIGHT_LIMIT) {
                break; // Прекращаем добавление задач, если достигнут лимит веса
            }
            futures.add(pool.submit(new TransferTask(weight))); //Добавляем новую задачу
        }

        for (Future<?> future : futures) {
            future.get(); // Дожидаемся завершения всех задач
        }

        pool.shutdown();
        System.out.println("Общий вес перенесенных товаров: " + totalWeight.get() + " кг");
    }

    static class TransferTask implements Runnable {
        private final int weight;

        TransferTask(int weight) {
            this.weight = weight;
        }

        @Override
        public void run() {
            if (totalWeight.addAndGet(weight) <= WEIGHT_LIMIT) {
                System.out.println("Перенос товара весом: " + weight + " кг");
            } else {
                totalWeight.addAndGet(-weight); // Откатываем добавление веса, если превышен лимит
            }
        }
    }
}