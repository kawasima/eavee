package net.unit8.eavee;

public class MyEavValue implements EavValue {
    private EavField field;
    private String value;
    @Override
    public EavField getField() {
        return field;
    }

    @Override
    public void setField(EavField field) {
        this.field = field;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setStringValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyEavValue{" +
                "field=" + field +
                ", value='" + value + '\'' +
                '}';
    }
}
