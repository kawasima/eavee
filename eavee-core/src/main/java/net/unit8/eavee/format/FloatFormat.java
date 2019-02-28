package net.unit8.eavee.format;

import net.unit8.eavee.EavFormat;

public class FloatFormat implements EavFormat<Float> {
    @Override
    public Float formatValue(String input) {
        if (input == null) return null;
        return Float.parseFloat(input);
    }
}
