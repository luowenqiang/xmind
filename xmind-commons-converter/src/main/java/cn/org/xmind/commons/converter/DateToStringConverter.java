package cn.org.xmind.commons.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author rodney
 */
public class DateToStringConverter implements Converter<Date, String> {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

    @Override
    public String convert(Date source) {
        return dateTimeFormat.format(source);
    }
}
