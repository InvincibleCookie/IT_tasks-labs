package lab4;

import java.io.*; // Импорт пакета для работы с файлами

public class FileCopying {
    public static void main(String[] args) {
        FileReader reader = null; // Объявление переменной для чтения из файла
        FileWriter writer = null; // Объявление переменной для записи в файл

        try {
            reader = new FileReader("src/lab4/source.txt"); // Открываем файл "source.txt" для чтения
            writer = new FileWriter("src/lab4/destination.txt", false); // Открываем файл
            // "destination.txt" для записи (false означает перезапись файла, если он существует)
            int c; // Объявление переменной для хранения считанных символов
            while ((c = reader.read()) != -1) { // Чтение символов из файла в цикле
                writer.write((char) c); // Запись считанного символа в файл "destination.txt"
            }
        } catch (IOException ex) { // Обработка исключения, еси возникают проблемы при чтении/записи файла
            System.out.println("Ошибка при чтении/записи файла: " + ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Закрытие файла для чтения
                }
                if (writer != null) {
                    writer.close(); // Закрытие файла для записи
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файлов: " + e.getMessage());
            }
        }
    }
}
