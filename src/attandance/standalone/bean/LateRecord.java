/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.bean;

import attandance.standalone.utils.DateHelper;
import java.util.Date;

/**
 *
 * @author huuuuxin
 */
public class LateRecord {
    private String staffName;
    private Date lateDate;
    private String caculateDateString;
    private String lateTimeDesc;
    
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getLateDate() {
        return lateDate;
    }

    public void setLateDate(Date lateDate) {
        this.lateDate = lateDate;
    }

    
    public String getCaculateDateString() {
        return caculateDateString;
    }

    public void setCaculateDateString(String caculateDateString) {
        this.caculateDateString = caculateDateString;
    }

    public String getLateTimeDesc() {
        return lateTimeDesc;
    }

    public void setLateTimeDesc(String lateTimeDesc) {
        this.lateTimeDesc = lateTimeDesc;
    }
    
    public LateRecord(String staffName, Date lateDate, String lateTimeDesc) {
        this.staffName = staffName;
        this.lateDate = lateDate;
        this.lateTimeDesc = lateTimeDesc;
        this.caculateDateString = DateHelper.toOnlyDateString(lateDate);
    }
}
