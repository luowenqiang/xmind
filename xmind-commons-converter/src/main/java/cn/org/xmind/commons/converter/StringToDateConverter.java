package cn.org.xmind.commons.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author rodney
 */
public class StringToDateConverter implements Converter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT_WITHOUT_SEC = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateFormat dateTimeFormatWithoutSec = new SimpleDateFormat(DATE_TIME_FORMAT_WITHOUT_SEC);
    private static final DateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

    @Override
    public Date convert(String source) {
        Date date = null;
        try {
            if (source.matches("^\\d{4}-\\d*-\\d*$")) {
                //符合yyyy-MM-dd
                date = dateFormat.parse(source);
            } else if (source.matches("^\\d{4}-\\d*-\\d*\\s*\\d*:\\d*$")) {
                //符合yyyy-MM-dd HH:mm
                date = dateTimeFormatWithoutSec.parse(source);
            } else if (source.matches("^\\d{4}-\\d*-\\d*\\s*\\d*:\\d*:\\d*$")) {
                //符合yyyy-MM-dd HH:mm:ss
                date = dateTimeFormat.parse(source);
            } else {
                throw new IllegalArgumentException("字符串" + source
                        + "无法转换为Date类型，请确保字符串时间格式是：yyyy-MM-dd、yyyy-MM-dd HH:mm、yyyy-MM-dd HH:mm:ss三种格式中的一种");
            }
        } catch (ParseException ex) {
            throw new IllegalArgumentException("字符串" + source
                    + "无法转换为Date类型：" + ex.getLocalizedMessage(), ex);
        }
        return date;
    }

    public static void main(String[] args) {
        StringToDateConverter converter = new StringToDateConverter();
        System.out.println(converter.convert("2012-2-23"));
        System.out.println(converter.convert("2012-2-23 23:23:2"));
        System.out.println(converter.convert("2012-2-23 5:2:1"));
        System.out.println(converter.convert("2012-2-2"));
        System.out.println(converter.convert("2012-2-2  2:21:05"));
        System.out.println(converter.convert("2012-02-02  01:23:32"));
        System.out.println(converter.convert("2012-02-02  01:23"));
    }
}
