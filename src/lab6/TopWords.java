package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class TopWords {
    public static void main(String[] args) {
        // указываем путь к файлу
        String filePath = "C:\\Users\\stav2\\IdeaProjects\\IT_labs\\src\\lab6\\TopWords.txt";
        // создаем объект File
        File file = new File(filePath);
        // создаем объект Scanner для чтения файла
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // создаем объект HashMap для хранения слов и их количества
        HashMap<String, Integer> wordsCount = new HashMap<>();
        // читаем файл по словам и добавляем их в Map
        try {
            // Пока в файле есть следующее слово
            while (scanner.hasNext()) {
                // Читаем следующее слово
                String word = scanner.next();

                // Проверяем, содержится ли слово в HashMap
                if (wordsCount.containsKey(word)) {
                    // Если слово уже есть в HashMap, увеличиваем счетчик
                    int count = wordsCount.get(word);
                    wordsCount.put(word, count + 1);
                } else {
                    // Если слова нет в HashMap, добавляем его со значением 1
                    wordsCount.put(word, 1);
                }
            }
            // закрываем Scanner
            scanner.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        // Создаем список из элементов Map
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordsCount.entrySet());
        // сортируем список по убыванию количества повторений
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // Сравниваем по убыванию значения (количество повторений)
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // выводим топ-10 слов
        for (int i = 0; i < 10 & i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
