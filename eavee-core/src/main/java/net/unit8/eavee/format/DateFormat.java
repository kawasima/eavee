package net.unit8.eavee.format;

import net.unit8.eavee.EavFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat implements EavFormat<Date> {
    @Override
    public Date formatValue(String input) {
        if (input == null) return null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        try {
            return df.parse(input);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
