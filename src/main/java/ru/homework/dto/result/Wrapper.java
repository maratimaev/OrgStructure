package ru.homework.dto.result;

/** Класс - обертка для выводимых данных
 * @param <T>
 */
public class Wrapper<T> {

    private T result;

    public Wrapper() {
    }

    public Wrapper(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
