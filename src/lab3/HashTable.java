package lab3;

import java.util.LinkedList;

public class HashTable<K, V> { // <K, V> - параметры, представляющие типы данных ключей и значений
    private LinkedList<Entry<K, V>>[] table; // Массив связных списков для хранения данных
    private int size; // Количество элементов в хэш-таблице

    public HashTable() {
        this(10); // Конструктор по умолчанию
    }

    public HashTable(int initialCapacity) {
        table = new LinkedList[initialCapacity]; // Создание массива связных списков
        size = 0; // Изначально хэш-таблица пуста
    }

    public int size() {
        return size; // Возвращает количество элементов в хэш-таблице
    }

    public boolean isEmpty() {
        return size == 0; // Проверяет, пуста ли хэш-таблица
    }

    public void put(K key, V value) {
        int index = hash(key); // Вычисление индекса массива на основе хэша ключа
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>(); // Создание нового связного списка, если в данном индексе его нет
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value); // Если ключ уже существует, обновляем его значение
                return;
            }
        }
        table[index].add(new Entry<K, V>(key, value)); // Добавление новой пары "ключ-значение" в связный список
        size++; // Увеличение счетчика элементов в хэш-таблице
    }

    public V get(K key) {
        int index = hash(key); // Вычисление индекса массива на основе хэша ключа
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue(); // Возвращает значение по ключу, если ключ найден
                }
            }
        }
        return null; // Если ключ не найден, возвращает null
    }

    public void remove(K key) {
        int index = hash(key); // Вычисление индекса массива на основе хэша ключа
        if (table[index] != null) {
            LinkedList<Entry<K, V>> list = table[index];
            for (Entry<K, V> entry : list) {
                if (entry.getKey().equals(key)) {
                    list.remove(entry); // Удаляет пару "ключ-значение" по ключу
                    size--; // Уменьшает счетчик элементов в хэш-таблице
                    return;
                }
            }
        }
    }

    private int hash(K key) {// Метод для определения индекса Entry<K, V> в таблице
        int index = key.hashCode() % table.length; // Берем остаток от деления хэш кода ключа на длину массива
        return (index < 0) ? index + table.length : index; //Возвращаем индекс Entry<K, V> в таблице
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key; // Возвращает ключ пары "ключ-значение"
        }

        public V getValue() {
            return value; // Возвращает значение пары "ключ-значение"
        }

        public void setValue(V value) {
            this.value = value; // Устанавливает новое значение для пары "ключ-значение"
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashMap = new HashTable<>();

        // Добавление пар "ключ-значение" в хэш-таблицу
        hashMap.put("apple", 5);
        hashMap.put("banana", 5);
        hashMap.put("orange", 7);

        // Получение значений по ключам
        //System.out.println(hashMap.get("apple"));
        //System.out.println(hashMap.get("banana"));
        //System.out.println(hashMap.get("orange"));

        //Удаление элемента из таблицы
        hashMap.remove("banana");
        //System.out.println(hashMap.get("banana"));

        //Смотрим является ли таблица пустой и выводим её значенме
        System.out.println(hashMap.size());
        System.out.println(hashMap.isEmpty());
    }
}
