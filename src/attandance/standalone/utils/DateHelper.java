/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author huuuuxin
 */
public class DateHelper {
    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
     public static final String ONLY_DATE_FORMAT = "yyyy-MM-dd";
    public static final DateFormat onlyDateFormat = new SimpleDateFormat(ONLY_DATE_FORMAT);
    public static Date convert(String value) {
        //2017-02-03 8:19:44
        Date date = null;
        try {
            date = dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String toOnlyDateString(Date date) {
        return onlyDateFormat.format(date);
    }
    public static String toDateString(Date date) {
        return dateFormat.format(date);
    }
        
    public static Date convertOnlyDate(String value) {
        //2017-02-03 8:19:44
        Date date = null;
        try {
            date = onlyDateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
