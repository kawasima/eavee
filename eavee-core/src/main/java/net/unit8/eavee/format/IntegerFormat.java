package net.unit8.eavee.format;

import net.unit8.eavee.EavFormat;

public class IntegerFormat implements EavFormat<Integer> {
    @Override
    public Integer formatValue(String input) {
        if (input == null) return null;

        return Integer.parseInt(input);
    }
}
