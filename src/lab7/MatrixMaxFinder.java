package lab7;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;

public class MatrixMaxFinder {
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) throws Exception {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 22, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        //Создаём список из объектов Future, которые будут хранить тип Integer
        //Future позволяет дождаться завершения асинхронной задачи, после чего извлечь её результат
        List<Future<Integer>> futures = new ArrayList<>();

        int rowsPerThread = matrix.length / THREAD_COUNT;
        for (int i = 0; i < THREAD_COUNT; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i + 1) * rowsPerThread;
            if (i == THREAD_COUNT - 1) {
                endRow = matrix.length; // Последний поток обрабатывает оставшиеся строки
            }
            futures.add(executor.submit(new FindMaxTask(matrix, startRow, endRow)));
        }

        int max = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            max = Math.max(max, future.get());
        }

        executor.shutdown();
        System.out.println(max);
    }

    static class FindMaxTask implements Callable<Integer> {
        private final int[][] matrix;
        private final int startRow, endRow;

        FindMaxTask(int[][] matrix, int startRow, int endRow) {
            this.matrix = matrix;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public Integer call() {
            int localMax = Integer.MIN_VALUE;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    localMax = Math.max(localMax, matrix[i][j]);
                }
            }
            return localMax;
        }
    }
}

