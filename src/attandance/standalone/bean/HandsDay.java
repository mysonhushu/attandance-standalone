/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.bean;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author huuuuxin
 */
public class HandsDay implements Comparator {
    private Date handDate;
    private int  handEmployCount = 0;

    public HandsDay(Date date) {
        this.handDate = date;
        this.handEmployCount = this.handEmployCount + 1;
    }
    @Override
    public int compare(Object o1, Object o2) {
       if(o1 instanceof HandsDay && o2 instanceof HandsDay) {
           HandsDay day1 = (HandsDay) o1;
           HandsDay day2 = (HandsDay) o2;
           if( day1.handDate.getTime() < day2.handDate.getTime()) {
               return -1;
           } else if (day1.handDate.getTime() > day2.handDate.getTime()){
               return 1;
           } else {
               return 0;
           }
       }
       return -1;
    }
    
    public HandsDay increase() {
        this.handEmployCount = this.handEmployCount + 1;
        return this;
    }
    
    public Date getHandDate() {
        return handDate;
    }

    public void setHandDate(Date handDate) {
        this.handDate = handDate;
    }

    public int getHandEmployCount() {
        return handEmployCount;
    }

    public void setHandEmployCount(int handEmployCount) {
        this.handEmployCount = handEmployCount;
    }

}
