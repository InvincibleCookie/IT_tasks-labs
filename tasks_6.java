import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Stack;

public class tasks_6 {
    public static void main(String[] args) throws Exception {
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(isExact(40320)));
        System.out.println(fractions("3.(142857)"));
        System.out.println(pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        try {
            System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)")); // ➞ -17
            System.out.println(generateNonconsecutive("-1 + 2")); // ➞ 0
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
    }

    public static String hiddenAnagram(String first, String second) {
        //Записываем первую и вторую строки, убирая из неё знаки пунктуации, цифры и спец. символы
        first = first.replaceAll("[ \\p{Punct}0-9]", "").toLowerCase();
        second = second.replaceAll("[ \\p{Punct}0-9]", "").toLowerCase();
        String second_2 = String.valueOf(second); //Создаем копию второй строки
        StringBuilder anagram = new StringBuilder(); //Сюда записываем анаграмму
        for (int i = 0; i < first.length(); i++) { //Цикл от 0 до конца первого слова
            for (int j = i; j < first.length(); j++) { //Цикл от i до конца первого слова
                if (second_2.length() == 0) {
                    //Если второе слово больше не содержит символов, то возвращаем анаграмму
                    return anagram.toString();
                }
                if (second_2.contains(String.valueOf(first.charAt(j)))) {
                    //Если второе слово содержит букву из первого слова, то убираем эту букву из копии второго слова
                    second_2 = second_2.replaceFirst(String.valueOf(first.charAt(j)), "");
                    anagram.append(String.valueOf(first.charAt(j))); //Добавляем эту букву к анаграмме
                } else {
                    //Иначе перезаписываем string_2 на исходное второе слово (Чтобы вернуть те буквы, что удалили
                    second_2 = String.valueOf(second);
                    anagram = new StringBuilder();
                    //Останавливаем второй цикл, чтобы начать искать анаграмму начиная со следующей буквы слова
                    break;
                }
            }
        }
        return "notfound";
    }

    public static String[] collect(String text, int n) {
        String[] collected = new String[text.length() / n]; //Создаем массив из элементов text.length()/n
        toCollect(text, n, collected, 0); //Вызываем рекурсивную функцию для заполнения массива
        Arrays.sort(collected); //Лексикографически упорядочим массив
        return collected;
    }

    public static void toCollect(String text, int n, String[] collected, int indx) {
        //Заполняем элемент под номером индекс подстрокой от indx * n до (indx + 1) * n
        collected[indx] = text.substring(indx * n, (indx + 1) * n);
        if (indx == collected.length - 1) { //Если индекс равен длине массива - 1, выходим из рекурсивной функции
            return;
        }
        toCollect(text, n, collected, indx + 1); //Иначе вызываем ещё раз эту функцию, увеличив indx на 1
    }

    public static String nicoCipher(String message, String key) {
        HashMap<Character, ArrayList<Character>> code = new HashMap<>(); //HashMap для хранения списков по букве ключа


        char[] charArray = key.toCharArray();
        Arrays.sort(charArray);
        String sortedKey = new String(charArray); //Сортируем наш ключ в алфавитном порядке

        //Округляем вверх отношения длин message к key
        int n = (int) Math.ceil((double) (message.length() / key.length()));

        for (int i = 0; i <= n; i++) { //Перебираем все строчки таблицы
            for (int j = 0; j < key.length(); j++) {//Перебираем столбцы
                code.computeIfAbsent(key.charAt(j), k -> new ArrayList<>()); //Создаем запись в HashMap если ключа нет
                if ((i * key.length() + j) < message.length()) { //Если в message ещё есть символы
                    code.get(key.charAt(j)).add(message.charAt(i * key.length() + j)); //Добавляем следующий символ
                } else {
                    code.get(key.charAt(j)).add(' '); //Если в message ничего больше нет, добавляем пробел
                }
            }
        }
        StringBuilder cipher = new StringBuilder(); //Переменная нашего шифра
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < sortedKey.length(); j++) {
                //Пробегаемся по всем ячейкам, но столбцы берем из отсортированного ключа
                cipher.append(code.get(sortedKey.charAt(j)).get(i)); //Добавляем символ к шифру
            }
        }
        return cipher.toString();
    }

    public static int[] twoProduct(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] * arr[j] == n) {
                    return new int[]{arr[j], arr[i]};
                }
            }
        }
        return new int[0];
    }

    public static int[] isExact(int n) {
        return isExact(n, 1, 1);
    }

    public static int[] isExact(int n, int c, int factorial) {
        if (factorial > n) {
            return new int[0];
        }
        if (factorial == n) {
            return new int[]{n, c};
        }
        return isExact(n, c + 1, factorial * (c + 1));
    }

    public static String fractions(String fr) {
        // Определяем регулярное выражение для извлечения целой части числа, числа после точки и числа в скобках
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)?\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(fr);

        int Y = 0;
        String m = null;
        String k = null;
        if (matcher.matches()) {
            Y = Integer.parseInt(matcher.group(1)); //Целая часть
            m = matcher.group(2); //Дробная часть до периода
            k = matcher.group(3); //Число в периоде
        }
        int numerator = 0;
        int denominator = 1;

        //В зависимости от того, есть ли у нас дробная часть до периода, по формуле записываем числитель и знаменатель
        if (m != null) {
            numerator = Integer.parseInt(m + k) - Integer.parseInt(m);
            denominator = Integer.parseInt("9".repeat(k.length()) + "0".repeat(m.length()));
        } else {
            assert k != null;
            numerator = Integer.parseInt(k);
            denominator = Integer.parseInt("9".repeat(k.length()));
        }

        int GCD = findGCD(numerator, denominator); //Находим НОД числителя и знаменателя для сокращения дроби
        return String.valueOf(numerator / GCD + Y * denominator / GCD) + "/" + String.valueOf(denominator / GCD);
    }

    // Метод для нахождения НОД по алгоритму Евклида
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static String pilish_string(String text) {
        int[] pi = new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9}; //Записываем массив из цифр числа пи
        int n = 0; //Число n нам нужно для обозначения границы в строке
        boolean flag = false; //Флаг для возвращения значения функции
        StringBuilder pi_string = new StringBuilder(); //Наша строка
        for (int k : pi) { //Перебираем каждую цифру, записанную в массив
            for (int j = n; j < n + k; j++) {//От границы n до n + цифра в массиве будем записывать строку
                if (j < text.length()) {
                    pi_string.append(text.charAt(j)); //Если j не превысила длину строки, значит продолжим записывать
                } else { //Если в самом начале следующего цикла мы превысили длину строки, то возвращаем pi_string
                    if (j == n) {
                        return pi_string.toString();
                    }
                    //В ином случае мы будем записывать последний записанный элемент и обозначим flag = 3
                    //Чтобы при завершении цикла мы вернули pi_string
                    pi_string.append(pi_string.charAt(j - 1));
                    flag = true;
                }
            }
            if (flag) {
                return pi_string.toString();
            } else {//Если флаг всё ещё false, добавим пробел к записываемой строке и увеличим нижнюю границу n
                pi_string.append(" ");
                n += k;
            }
        }
        return pi_string.toString();
    }
    public static double generateNonconsecutive(String expression) throws Exception {
        String postfix = infixToPostfix(expression.replaceAll("\\s+", "")); //Удаляем пробелы
        return evaluatePostfix(postfix); // Вычисление постфиксного выражения (Условно 1 + 1 преобразовыввается в 1 1 +)
    }

    private static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean lastWasOp = true; // Флаг для обнаружения отрицательного числа

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') { // Если символ - цифра или точка, добавляем его к результату
                result.append(c);
                lastWasOp = false;

            // Обработка открывающей скобки
            } else if (c == '(') {
                stack.push(c);
                lastWasOp = true;
            // Обработка закрывающей скобки
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(' ').append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                lastWasOp = false;
            } else {
                result.append(' ');
                // Обработка отрицательного числа
                if (c == '-' && lastWasOp) {
                    result.append("0 ");
                }
                // Выталкивание операторов из стека с более высоким или равным приоритетом
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(' ');
                }
                // Добавление текущего оператора в стек
                stack.push(c);
                lastWasOp = true;
            }
        }
        // Выталкивание оставшихся операторов из стека
        while (!stack.isEmpty()) {
            result.append(' ').append(stack.pop());
        }
        return result.toString();
    }

    // Метод для определения приоритета оператора
    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // Метод для вычисления постфиксного выражения
    private static double evaluatePostfix(String expression) throws Exception {
        Stack<Double> stack = new Stack<>();
        for (String token : expression.split("\\s")) {
            if (token.isEmpty())
                continue;

            // Если токен - число, помещаем его в стек
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                // Выполнение операции
                double val1 = stack.pop();
                double val2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(val2 + val1);
                        break;
                    case "-":
                        stack.push(val2 - val1);
                        break;
                    case "*":
                        stack.push(val2 * val1);
                        break;
                    case "/":
                        // Проверка деления на ноль
                        if (val1 == 0)
                            throw new Exception("Division by zero.");
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        // Возвращаем результат, который остался в стеке
        return stack.pop();
    }

    public static String isValid(String str) {
        // Создаем HashMap для хранения символов и частоты их встречаемости
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        // Создаем HashMap для хранения частот встречаемости символов в качестве ключа и количество повторений
        // этих частот в качестве значения
        Map<Integer, Integer> digitCount = new HashMap<>();
        for (int count : charCount.values()) {
            digitCount.put(count, digitCount.getOrDefault(count, 0) + 1);
        }
        //Создаем список, который будет состоять из массивов, содержащих ключ и значения из DigitCount
        List<int[]> valuesList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : digitCount.entrySet()) {
            valuesList.add(new int[]{entry.getKey(), entry.getValue()});
        }
        //Если размер списка 1, то это значит, что каждый символ встречается одинаковое количество раз
        if (valuesList.size() == 1){
            return "YES";
        }
        //Если размер списка 2, то значит в нашей строке есть две разные частоты повторений символов,
        // чтобы выполнялось условие в этом случае, у одной из этих двух частот должно быть только одно
        //количество повторений и если мы уменьшим эту частоту на 1, оно должно стать равно второй частоте
        if (valuesList.size() == 2){
            if (valuesList.get(0)[1] == 1 && (valuesList.get(0)[0] - 1) == valuesList.get(1)[0]
                || valuesList.get(1)[1] == 1 && (valuesList.get(1)[0] - 1) == valuesList.get(0)[0]) {
                return "YES";
            }
        }
        return "NO"; //Во всех остальных случаях условие не выполняется
    }
    public static String findLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Создаем матрицу для хранения промежуточных результатов
        int[][] dp = new int[m + 1][n + 1];

        // Заполняем матрицу dp
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    // Если одна из строк пуста, то длина НОП равна 0
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // Если символы совпадают, увеличиваем длину НОП
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Если символы не совпадают, берем максимум из двух соседних ячеек
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Построение НОП
        int index = dp[m][n]; // Длина НОП
        char[] lcs = new char[index]; // Массив для хранения НОП
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                // Если символы совпадают, добавляем их к НОП и двигаемся вверх и влево
                lcs[index - 1] = str1.charAt(i - 1);
                i--;
                j--;
                index--;
                // Если символы не совпадают, двигаемся в направлении более высокого значения в матрице dp
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }
}
