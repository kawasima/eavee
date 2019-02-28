package net.unit8.eavee;

import net.unit8.eavee.format.*;

import java.lang.reflect.InvocationTargetException;

public enum EavFormatType {
    BOOLEAN(BooleanFormat.class),
    DATE(DateFormat.class),
    FLOAT(FloatFormat.class),
    INTEGER(IntegerFormat.class),
    TEXT(StringFormat.class),
    LONG_TEXT(StringFormat.class);

    private EavFormat instance;

    <T> EavFormatType(Class<? extends EavFormat<T>> clazz) {
        try {
            this.instance = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(clazz.toString());
        }
    }

    public <T> EavFormat<T> getInstance() {
        return instance;
    }
}
