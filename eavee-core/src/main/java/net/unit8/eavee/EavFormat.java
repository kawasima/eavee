package net.unit8.eavee;

public interface EavFormat<T> {
    T formatValue(String input);

    default boolean isValid(String value) {
        return true;
    }
}
