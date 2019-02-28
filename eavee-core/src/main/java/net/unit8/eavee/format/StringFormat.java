package net.unit8.eavee.format;

import net.unit8.eavee.EavFormat;

public class StringFormat implements EavFormat<String> {
    @Override
    public String formatValue(String input) {
        return input;
    }
}
