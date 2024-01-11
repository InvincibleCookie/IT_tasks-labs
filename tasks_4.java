import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class tasks_4 {

    public static void main(String[] args) {
        //System.out.println(nonRepeatable("abracadabra"));
        //System.out.println(generateBrackets(2));
        //System.out.println(binarySystem(5));
        //System.out.println(binarySystem2(3));
        //System.out.println(alphabeticRow("fefefewxyz"));
        System.out.println(countAndSort("ccaaabbccccdd"));
        System.out.println(convertToNum("one hundred sixty eight"));
        //System.out.println(uniqueSubstring("77897898"));
        int[][] matrix1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(shortestWay(matrix1));

        int[][] matrix2 = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(shortestWay(matrix2));
        System.out.println(numericOrder("t3o the4m 1One all6 r5ule ri2ng"));
        //System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        //System.out.println(switchNums(491, 3912));
    }
    public static String nonRepeatable(String str) {
        // Если строка пуста, возвращаем ""
        if (str.isEmpty()) {
            return str;
        }

        //Получаем текущий символ
        char currentChar = str.charAt(0);

        // Удаляем все вхождения текущего символа в оставшейся части строки
        String remainingString = str.replaceAll(String.valueOf(currentChar), "");

        // Рекурсивный вызов для оставшейся части строки
        return currentChar + nonRepeatable(remainingString);
    }

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    // Вспомогательная рекурсивная функция для генерации комбинаций скобок.
    private static void generate(List<String> result, String current, int open, int close, int n) {
        //если текущая строка содержит 2*n символов (открывающих и закрывающих скобок),
        //то добавляем её в список результатов и завершаем текущий вызов функции.
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Рекурсивно вызываем функцию, добавляя открывающую скобку
        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }

        // Рекурсивно вызываем функцию, добавляя закрывающую скобку
        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }
    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        binary(result, "",  n, 0);
        return result;
    }
    private static void binary(List<String> result, String current, int n, int c){

        // Если количество символов в текущей строке достигло n,
        // добавляем текущую строку в список результатов и завершаем текущий вызов функции.
        if (c == n){
            result.add(current);
            return;
        }
        // Рекурсивно вызываем функцию, добавляя '0' к текущей строке, только если
        // предыдущий символ был '1' или если это первый символ.
        if (c == 0 || current.charAt(current.length() - 1) == '1'){
            binary(result, current + "0", n, c + 1);
        }
        // Рекурсивно вызываем функцию, добавляя '1' к текущей строке, только если
        // текущая строка пуста или предыдущий символ был '0'.
        if (current.isEmpty() || current.charAt(current.length() - 1) == '0') {
            binary(result, current + "1", n, c + 1);
        }

    }
    public static List<String> binarySystem2(int n) {
        List<String> result = new ArrayList<>();
        binary2(result, "",  n);
        return result;
    }
    private static void binary2(List<String> result, String current, int n) {
        if (current.length() == n) {
            result.add(current);
            return;
        }

        // Добавляем '0' к текущей строке, но только если предыдущий символ не '0'
        if (current.length() == 0 || current.charAt(current.length() - 1) != '0') {
            binary2(result, current + "0", n);
        }
        // Добавляем '1' к текущей строке и вызываем рекурсивно функцию binary2
        binary2(result, current + "1", n);

    }

    public static String alphabeticRow(String str) {


        String currentRow = Character.toString(str.charAt(0)); // Инициализация текущего ряда первым символом строки
        String longestRow = currentRow;

        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i); // Текущий символ
            char previousChar = str.charAt(i - 1); // Предыдущий символ

            // Проверяем, образует ли текущий символ алфавитный ряд с предыдущим символом
            // И также проверяем не повторяются ли символы через 1
            if ((currentChar == previousChar + 1 || currentChar == previousChar - 1) && (previousChar != currentChar + 1)) {
                currentRow += currentChar; // Если да, добавляем его к текущему ряду.
            } else {
                currentRow = Character.toString(currentChar); // Если нет, начинаем новый ряд с текущего символа.
            }

            if (currentRow.length() > longestRow.length()) {
                longestRow = currentRow; // Если текущий ряд длиннее, обновляем самый длинный ряд.
            }
        }

        return longestRow;
    }
    public static String countAndSort(String str) {
        List<String> patterns = new ArrayList<>(); // Создаем список для хранения букв и кол-во их повторений подряд
        int count = 1; // Счетчик для подсчета повторений символа.
        char prev = str.charAt(0); // Первый символ строки.

        //Заполняем список символами и кол-вом их повторений
        for (int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);

            if (current == prev) {
                count++;
            } else {
                patterns.add(prev + Integer.toString(count));
                count = 1;
                prev = current;
            }
        }

        patterns.add(prev + Integer.toString(count));

        // Сортировка пузырьком по значению int от второго до последнего символа в строке.
        for (int i = 0; i < patterns.size() - 1; i++) {
            for (int j = 0; j < patterns.size() - 1 - i; j++) {
                int value1 = Integer.parseInt(patterns.get(j).substring(1)); // Получаем значение для первой строки.
                int value2 = Integer.parseInt(patterns.get(j + 1).substring(1)); // Получаем значение для второй строки.

                if (value1 > value2) {
                    String temp = patterns.get(j);
                    patterns.set(j, patterns.get(j + 1));
                    patterns.set(j + 1, temp);
                }
            }
        }

        // Собираем отсортированные буквенные паттерны в результирующую строку.
        StringBuilder result = new StringBuilder();
        for (String pattern : patterns) {
            result.append(pattern);
        }

        return result.toString();
    }

// Создаем HashMap для сопоставления слов числовым значениям
    public static int convertToNum(String input) {
        HashMap<String, Integer> wordToNum = new HashMap<>();
        wordToNum.put("zero", 0);
        wordToNum.put("one", 1);
        wordToNum.put("two", 2);
        wordToNum.put("three", 3);
        wordToNum.put("four", 4);
        wordToNum.put("five", 5);
        wordToNum.put("six", 6);
        wordToNum.put("seven", 7);
        wordToNum.put("eight", 8);
        wordToNum.put("nine", 9);
        wordToNum.put("ten", 10);
        wordToNum.put("eleven", 11);
        wordToNum.put("twelve", 12);
        wordToNum.put("thirteen", 13);
        wordToNum.put("fourteen", 14);
        wordToNum.put("fifteen", 15);
        wordToNum.put("sixteen", 16);
        wordToNum.put("seventeen", 17);
        wordToNum.put("eighteen", 18);
        wordToNum.put("nineteen", 19);
        wordToNum.put("twenty", 20);
        wordToNum.put("thirty", 30);
        wordToNum.put("forty", 40);
        wordToNum.put("fifty", 50);
        wordToNum.put("sixty", 60);
        wordToNum.put("seventy", 70);
        wordToNum.put("eighty", 80);
        wordToNum.put("ninety", 90);
        wordToNum.put("hundred", 100);

        // Разделяем входную строку на слова, используя пробел как разделитель
        String[] words = input.split(" ");
        int result = 0;
        int currentNumber = 0;

        for (int i = 0; i < words.length; i++) {
            int value = wordToNum.get(words[i]);
            //Если следующее словаа hundred - умножаем текущее значение на 100
            if (i < words.length - 1 && words[i + 1].equals("hundred")) {
                result += value * 100; //Записываем в результат
            }
            else if (value != 100){
                result += value;
            }
        }

        return result;
    }

    public static String uniqueSubstring(String s) {
        int maxLength = 0; // Длина максимальной уникальной подстроки
        int start = 0;  // Начальный индекс максимальной уникальной подстроки
        int end = 0; // Конечный индекс максимальной уникальной подстроки
        int currentStart = 0; // Начальный индекс текущей подстроки с уникальными символами
        HashMap<Character, Integer> charIndexMap = new HashMap<>(); // HashMap для отслеживания индексов символов в строке

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Если символ уже встречался и его индекс больше или равен начальному индексу текущей подстроки
            if (charIndexMap.containsKey(c) && charIndexMap.get(c) >= currentStart) {
                currentStart = charIndexMap.get(c) + 1; // Сдвигаем начальный индекс текущей подстроки
            }

            charIndexMap.put(c, i); // Обновляем индекс символа в HashMap

            // Если длина текущей подстроки с уникальными символами больше максимальной
            if (i - currentStart > maxLength) {
                maxLength = i - currentStart;
                start = currentStart;
                end = i;
            }
        }
        // Извлекаем максимальную уникальную подстроку из исходной строки и возвращаем её
        return s.substring(start, end + 1);
    }
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;

        // Создаем массив для хранения сумм путей
        int[][] dp = new int[n][n];

        // Заполняем последнюю ячейку
        dp[n-1][n-1] = matrix[n-1][n-1];

        // Заполняем последний столбец
        for (int i = n - 2; i >= 0; i--) {
            dp[i][n-1] = dp[i+1][n-1] + matrix[i][n-1];
        }

        // Заполняем последнюю строку
        for (int j = n - 2; j >= 0; j--) {
            dp[n-1][j] = dp[n-1][j+1] + matrix[n-1][j];
        }

        // Заполняем остальные ячейки
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
            }
        }

        // Возвращаем минимальную сумму пути от начала до конца
        return dp[0][0];
    }

    public static String numericOrder(String input) {
        // Разделяем входную строку на слова
        String[] words = input.split(" ");
        Map<Integer, String> wordMap = new HashMap<>();

        // Извлекаем числа из слов
        for (String word : words) {
            StringBuilder wordWithoutNumbers = new StringBuilder();
            StringBuilder numbers = new StringBuilder();

            for (char c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    numbers.append(c);
                } else {
                    wordWithoutNumbers.append(c);
                }
            }

            if (numbers.length() > 0) {
                int position = Integer.parseInt(numbers.toString());
                wordMap.put(position, wordWithoutNumbers.toString());
            }
        }

        // Сортируем слова в соответствии с числами
        List<String> sortedWords = new ArrayList<>();
        for (int i = 1; i <= wordMap.size(); i++) {
            sortedWords.add(wordMap.get(i));
        }

        // Собираем упорядоченные слова в новую строку
        return String.join(" ", sortedWords);
    }
    public static int switchNums(int num1, int num2) {
        // Преобразовываем числа в массивы цифр
        int[] digits1 = getDigits(num1);
        int[] digits2 = getDigits(num2);

        // Создаем копию массива digits1 для поиска максимальной неповторяющейся цифры
        int[] availableDigits = Arrays.copyOf(digits1, digits1.length);

        // Проходим по цифрам второго числа
        for (int i = 0; i < digits2.length; i++) {
            int max = -1;
            int maxIndex = -1;

            // Ищем максимальную неповторяющуюся цифру из доступных
            for (int j = 0; j < availableDigits.length; j++) {
                if (availableDigits[j] >= max) {
                    max = availableDigits[j];
                    maxIndex = j;
                }
            }

            // Если нашли подходящую цифру, заменяем текущую цифру во втором числе
            if (max >= digits2[i]) {
                digits2[i] = max;
                availableDigits[maxIndex] = -1; // Помечаем использованную цифру
            }
        }

        // Собираем цифры обратно в число
        int result = 0;
        for (int j : digits2) {
            result = result * 10 + j;
        }

        return result;
    }

    // Вспомогательная функция для разбиения числа на массив цифр
    public static int[] getDigits(int num) {
        String numStr = String.valueOf(num);
        int[] digits = new int[numStr.length()];

        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = numStr.charAt(i) - '0';
        }

        return digits;
    }
}
