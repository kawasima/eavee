package net.unit8.eavee;

public interface EavValue {
    EavField getField();
    void setField(EavField field);

    String getValue();
    void setStringValue(String value);

    default void setValue(Object value) {
        if (value == null) {
            setNullValue();
        } else {
            setStringValue(value.toString());
        }
    }

    default void setNullValue() {
        setStringValue(null);
    }
}
