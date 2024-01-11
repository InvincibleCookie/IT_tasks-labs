package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SumArray {
    private static final int THREAD_COUNT = 3; // Количество потоков в пуле

    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        AtomicInteger sum = new AtomicInteger(0);// AtomicInteger для потокобезопасного хранения результата
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT); //Создаем указанное количество потоков

        int partSize = array.length / THREAD_COUNT; // Размер одной части массива

        //Делим массив на части
        for (int i = 0; i < THREAD_COUNT; i++) {
            int start = i * partSize;
            int end = (i + 1) * partSize;
            if (i == THREAD_COUNT - 1) {
                end = array.length; // Обрабатываем последнюю часть массива
            }
            //Вызываем в нашем потоке класс SumTask с помощью которого в потоке посчитается отдельная часть массива
            executor.execute(new SumTask(array, start, end, sum));
        }

        executor.shutdown(); // Завершаем работу потоков
        executor.awaitTermination(1, TimeUnit.MINUTES); // Ожидаем завершения работы всех потоков

        System.out.println(sum);
    }

    static class SumTask implements Runnable {
        private final int[] array;
        private final int start, end;
        private final AtomicInteger sum;

        SumTask(int[] array, int start, int end, AtomicInteger sum) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        @Override
        public void run() {
            int partialSum = 0;
            for (int i = start; i < end; i++) {
                partialSum += array[i];
            }
            sum.addAndGet(partialSum); //Добавляем подсчитанную сумму к нашему классу
        }
    }
}

