package net.unit8.eavee.format;

import net.unit8.eavee.EavFormat;

public class BooleanFormat implements EavFormat<Boolean> {
    public Boolean formatValue(String input) {
        if (input == null) return false;

        return input.equalsIgnoreCase("true")
                || input.equals("1")
                || input.equalsIgnoreCase("T")
                || input.equalsIgnoreCase("on");
    }
}
