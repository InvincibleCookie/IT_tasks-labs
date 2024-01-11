import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class tasks_5 {
    public static void main(String[] args) {
        //System.out.println(sameLetterPattern("ABsAB", "CCD"));
        //System.out.println(spiderVsFly("A3", "D3"));
        //System.out.println(digitsCount(1289396387328L));
        //System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));

        int[][] sumUp = sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7});
        for (int i = 0; i < sumUp.length; i++){
            System.out.println(Arrays.toString(sumUp[i]));
        }
        //System.out.println(takeDownAverage(new String[] {"53%", "79%"}));
        //System.out.println(caesarCipher("encode", "almost last task!", 4));
        //System.out.println(setSetup(5, 3));
        //System.out.println(timeDifference("New York", "December 31, 1970 13:40", "New Delhi"));
        //System.out.println(isNew(2));
    }

    public static boolean sameLetterPattern(String a, String b) {
        if (a.length() == b.length()) { //Если у a и b разная длина, значит уже не выполняется условие
            // и мы можем отсечь эти варианты
            int n = 0; //Переменная для присвоения номера букве из строки
            HashMap<Character, Integer> hashmap_a = new HashMap<>(); //hashmap для a
            HashMap<Character, Integer> hashmap_b = new HashMap<>();//hashmap для b
            for (int i = 0; i < a.length(); i++) { //проходимся циклом по буквам в строках
                // Если у нас одновременно нет в словаре для каждой следующей буквы строки значения, значит
                //паттерн сохраняется и мы добавляем новые буквы в словарь
                if (!hashmap_a.containsKey(a.charAt(i)) && !hashmap_b.containsKey(b.charAt(i))) {
                    hashmap_a.put(a.charAt(i), n);
                    hashmap_b.put(b.charAt(i), n);
                    n += 1;
                    continue;

                    //Если у нас есть оба значения текущей буквы в словаре, то проверяем их значение в hashmap
                    //Если значения не равны, то паттерн не соблюдается и метод вернет false
                } else if (hashmap_a.containsKey(a.charAt(i)) && hashmap_b.containsKey(b.charAt(i))) {
                    if (Objects.equals(hashmap_a.get(a.charAt(i)), hashmap_b.get(b.charAt(i)))) {
                        continue;
                    }
                    return false; //
                }
                return false;

            }
            return true;
        }
        return false;
    }
    public static String spiderVsFly(String spider, String fly) {
        if (spider.charAt(0) == fly.charAt(0)){ // Если паук и муха на одном радиале
            StringBuilder path = new StringBuilder();
            int mn = 1;
            int i = Character.getNumericValue(spider.charAt(1));
            if (i > Character.getNumericValue(fly.charAt(1))) { //Если паук выше мухи, то мы будем отнимать от
                // его высоты 1, иначе мы будем добавлять её
                mn = -1;
            }
            while (i != Character.getNumericValue(fly.charAt(1))){ //Записываем координаты, по которым идём
                path.append(spider.charAt(0)).append(i).append("-");
                i += mn;
            }
            path.append(spider.charAt(0)).append(fly.charAt(1));
            return path.toString(); //Возвращаем значение
        }
        else {
            //Длина маршрута, если дойти с радиала до нулевой точки и подняться на нужный радиал
            int min = Character.getNumericValue(spider.charAt(1)) + Character.getNumericValue(fly.charAt(1));

            //Вычисляем сколько нам надо, чтобы перейти с радиала паука на радиал мухи
            int radDif = Math.abs(spider.charAt(0) - fly.charAt(0));

            if (radDif > 8 - radDif) {
                radDif = 8 - radDif;
            }
            //Вычисляем маршрут, если паук дойдет до одной с мухой кольца и перейдет по радиалам до неё
            double way = Math.abs(Character.getNumericValue(spider.charAt(1)) - Character.getNumericValue(fly.charAt(1)));
            int i = Math.abs(Character.getNumericValue(spider.charAt(1)) - (int) way);
            way += radDif * Math.sqrt(i * i * 2 - 2 * i * i * Math.cos(Math.toRadians(45.0)));

            if (min < way) { // Если быстрее идти до центра, а потом до мухи
                StringBuilder path = new StringBuilder();
                int j = Character.getNumericValue(spider.charAt(1));
                while (j >= 0) {
                    path.append(spider.charAt(0)).append(j).append("-");
                    j--;
                }
                j += 2;
                while (j < Character.getNumericValue(fly.charAt(1))) {
                    path.append(fly.charAt(0)).append(j).append("-");
                    j++;
                }
                path.append(fly.charAt(0)).append(j);
                return path.toString();
            }
            else {// Если быстрее идти по радиалам
                char x = spider.charAt(0);
                int length = 0;

                //Нам надо понять быстрее по радиалам идти направо или налево, мы уже вычисляли выше radDif, который
                //показывал сколько раз нам надо, чтобы пройти до радиала. Попробуем вручную посчитать это и
                //Пойдем сначала направо
                while (x != fly.charAt(0)) {
                    if ((char) (x + 1) != 'I') {
                        x++;
                    }
                    else {
                        //Если у нас символ = I, то значит паук вернулся в начало радиала и сейчас паук на A
                        x = 'A';
                        }
                    length += 1;
                    }
                if (length == radDif) { // Если это равенство выполняется, то по радиалам надо идти направо
                    int j = Character.getNumericValue(spider.charAt(1));
                    int mn = 1;
                    if (j > Character.getNumericValue(fly.charAt(1))) {
                        mn = -1;
                    }
                    StringBuilder path = new StringBuilder();
                    while (j != Character.getNumericValue(fly.charAt(1))) {
                        path.append(spider.charAt(0)).append(j).append("-");
                        j += mn;
                    }
                    path.append(spider.charAt(0)).append(j).append("-");
                    x = spider.charAt(0);
                    while (x != fly.charAt(0)) {
                        if ((char) (x + 1) != 'I') {
                            x++;
                        } else {
                            x = 'A';
                        }
                        if (x != fly.charAt(0)) {
                            path.append(x).append(fly.charAt(1)).append("-");
                        }
                        else {
                            path.append(x).append(fly.charAt(1));
                        }
                    }
                    return path.toString();
                }
                else { //Если не выполняется, то идём налево
                    int j = Character.getNumericValue(spider.charAt(1));
                    int mn = 1;
                    if (j > Character.getNumericValue(fly.charAt(1))) {
                        mn = -1;
                    }
                    StringBuilder path = new StringBuilder();
                    while (j != Character.getNumericValue(fly.charAt(1))) {
                        path.append(spider.charAt(0)).append(j).append("-");
                        j += mn;
                    }
                    path.append(spider.charAt(0)).append(j).append("-");
                    x = spider.charAt(0);
                    while (x != fly.charAt(0)) {
                        if ((char) (x - 1) != '@') {
                            x--;
                        } else {
                            x = 'H';
                        }
                        if (x != fly.charAt(0)) {
                            path.append(x).append(fly.charAt(1)).append("-");
                        }
                        else {
                            path.append(x).append(fly.charAt(1));
                        }
                    }
                    return path.toString();
                }
            }
        }

    }

    public static int digitsCount(long n) {
        if (n == 0) {
            return 1;
        }
        return digitsCount(n, 0);
    }

    private static int digitsCount(long n, int c) {
        if (n > 0) {
            return digitsCount(n / 10, c + 1);
        }
        return c;

    }

    public static int totalPoints(String[] scramble_worlds, String origin_word) {
        int c = 0; // Переменная для подсчёта очков
        for (String word : scramble_worlds) {
            boolean t_f = true; //Флаг для определения того, надо ли нам считать очки за текущее слово
            String origin_copy = origin_word; //Копия скремблированного слова
            for (int i = 0; i < word.length(); i++) {
                //Если у нас скремблированная строка содержит символ слова из массива, то мы заменяем первое вхождение
                //этого символа на пробел в копии скремблированного слова
                if (origin_copy.contains(String.valueOf(word.charAt(i)))) {
                    origin_copy = origin_copy.replace(word.charAt(i), ' ');
                } else {
                    //Если такого символа нет, то флаг получения очков = false и мы завершаем цикл
                    t_f = false;
                    break;
                }
            }
            if (t_f) {
                //В зависимости от длины слова, добавляем соответствующее количество очков
                switch (word.length()) {
                    case 3:
                        c += 1;
                        break;
                    case 4:
                        c += 2;
                        break;
                    case 5:
                        c += 3;
                        break;
                    case 6:
                        c += 54;
                }

            }
        }
        return c;
    }

    public static int[][] sumsUp(int[] nums) {
        int[][] result = new int[nums.length][2]; //Создаем двойной массив
        int indx = 0; //Переменная для определения индекса пары в массиве
        for (int i = 0; i < nums.length; i++) { //Перебираем все варианты пар чисел
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == 8)  {
                    if (nums[i] > nums[j]) {
                        result[indx][0] = nums[j];
                        result[indx][1] = nums[i];
                    }
                    else {
                        result[indx][0] = nums[i];
                        result[indx][1] = nums[j];
                    }
                    indx += 1;
                }
            }
        }
        int[][] newResult = new int[indx][2]; //Перезаписываем массив, указывая его точную итоговую размерность
        System.arraycopy(result, 0, newResult, 0, indx);

        return newResult;
    }

    public static String takeDownAverage(String[] scores) {
        int sum = 0;
        for (String s : scores) {
            // Удаляем знак процента (%) из строки и парсим оставшуюся часть в целое число
            String score = s.replace("%", "");
            sum += Integer.parseInt(score);
        }
        int result = (int) Math.round((scores.length + 1) * ((double) sum / scores.length - 5) - sum);
        //Переводим ответ в строку, добавляя % на конец
        return String.format("%d%%", result);
    }

    public static String caesarCipher(String mode, String word, int n) {
        word = word.toUpperCase();
        StringBuilder result = new StringBuilder();
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for (int i = 0; i < word.length(); i++) {

            if (Character.isLetter(word.charAt(i))) { //Если текущий символ - буква, то нам надо зашифровать её
                if (mode.equals("encode")) {
                    //Если у нас режим работы encode, то мы добавляем n к номеру буквы в алфавите
                    if ((int) word.charAt(i) - 65 + n < 26) {
                        result.append(letters[(int) word.charAt(i) - 65 + n]);
                    } else {
                        //Если мы выходим за пределы алфавита, то мы отнимаем 26 от номера позиции в алфавите + n
                        result.append(letters[((int) word.charAt(i) - 65 + n) - 26]);
                    }
                } else {
                    //Если у нас режим decode, то мы действуем аналогично encode, но теперь вычитаем n
                    if ((int) word.charAt(i) - 65 - n > 0) {
                        result.append(letters[(int) word.charAt(i) - 65 - n]);
                    } else {
                        result.append(letters[((int) word.charAt(i) - 65 - n) + 26]);
                    }
                }
            } else {
                //Если текущий символ не является буквой, то добавляем его, какой он есть
                result.append(word.charAt(i));
            }
        }
        return result.toString();
    }
    public static int setSetup(int n, int k) {
        int chisl = factorial(n);
        int znam = factorial(n - k);
        return chisl / znam;
    }
    private static int factorial(int n){
        if (n != 0){
            return n * factorial(n - 1);
        }
        return 1;
    }
    public static String timeDifference(String cityA, String timestamp, String cityB){
        //Создаём hashmap, чтобы через него получать ZoneId нужного города
        HashMap<String, String> greenwich = new HashMap<>();
        greenwich.put("Los Angeles", "America/Los_Angeles");
        greenwich.put("New York", "America/New_York");
        greenwich.put("Caracas", "America/Caracas");
        greenwich.put("Buenos Aires", "America/Argentina/Buenos_Aires");
        greenwich.put("London", "Europe/London");
        greenwich.put("Rome", "Europe/Rome");
        greenwich.put("Moscow", "Europe/Moscow");
        greenwich.put("Tehran", "Asia/Tehran");
        greenwich.put("New Delhi", "Asia/Kolkata");
        greenwich.put("Beijing", "Asia/Shanghai");
        greenwich.put("Canberra", "Australia/Canberra");

        //Создаём форму, с помощью которой будем получать дату из строки timestamp
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.UK);
        //Получаем введенную дату в формате объекта ZonedDateTime
        ZonedDateTime time = ZonedDateTime.parse(timestamp, form.withZone(ZoneId.of(cityA, greenwich)));
        //Переводим эту дату в нужный часовой пояс в нужном нам формате
        return time.withZoneSameInstant(ZoneId.of(cityB, greenwich)).format(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"));

    }

    public static boolean isNew(int int_n){
        String n = String.valueOf(int_n); //Переводим число в строку
        for(int i = 1; i < n.length();i++){ //Проходимся по каждой цифре в строке
            if (n.charAt(i) < n.charAt(0) && n.charAt(i) != '0') { //Если эта цифра не 0 и меньше первой цифры
                return false;                                      //Вовращаем false
            }
        }
        return true; //Если нет цифр, при которой программа возвращает false, значит надо вернуть true
    }

}

