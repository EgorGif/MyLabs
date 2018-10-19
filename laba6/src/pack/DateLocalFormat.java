package pack;

import java.util.*;
import java.text.*;
public class DateLocalFormat {

    public static String getTimeStyle(Date now,Locale currentLocale) {
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);
        return dateFormat.format(now);
    }
}