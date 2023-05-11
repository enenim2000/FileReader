package com.dot.FileReader.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtil {

    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static LocalDateTime parse(String date) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS.substring(0, date.length()));
        return LocalDateTime.parse(date, formatter);
    }

    public static String today() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.format(formatter);
    }
}
