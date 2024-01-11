package lab6;

import java.util.Arrays;

public class Stack<T> {
    private T[] data;
    private int size;

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (data.length == size) { //Если у нас количество элементов в стэке достигло размеров массива
            data = Arrays.copyOf(data, data.length + 1); //Возвращаем массив с увеличенной вместимостью
        }

        data[size++] = element; //записываем под индексом size следующий элемент массива и увеличиваем size на 1
    }

    public T pop() {
        if (size == 0) {
            return null;
        }

        return data[--size]; //Уменьшаем size на один и возвращаем элемент с индексом size
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return data[size - 1]; //Возвращаем последний элемент стэка
    }
}