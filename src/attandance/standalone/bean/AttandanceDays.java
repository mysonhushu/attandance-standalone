/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.bean;

import attandance.standalone.utils.DateHelper;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author huuuuxin
 */
public class AttandanceDays {
    Map<Date, HandsDay> daysList = Maps.newTreeMap();
   public  void countHandsDay(List<AttandanceRecord> records) {
       for(AttandanceRecord record: records) {
           Date keyDate = DateHelper.convertOnlyDate(record.getAttandanceDate());
           if(daysList.containsKey(keyDate)) {
               daysList.put(keyDate, daysList.get(keyDate).increase());
           } else {
               daysList.put(keyDate, new HandsDay(keyDate));
           }
       }
   }
   public Map<Date, HandsDay> getCountResult() {
       return daysList;
   }
}
