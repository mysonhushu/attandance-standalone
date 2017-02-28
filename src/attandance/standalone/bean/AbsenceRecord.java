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
public class AbsenceRecord {
    private String staffName;
    private Date absenceDate;
    private String caculateDateString;

    public String getCaculateDateString() {
        return caculateDateString;
    }

    public void setCaculateDateString(String caculateDateString) {
        this.caculateDateString = caculateDateString;
    }

    @Override
    public String toString() {
        return "AbsenceRecord{" + "staffName=" + staffName + ", absenceDate=" + absenceDate + ", caculateDateString=" + caculateDateString + '}';
    }

    public AbsenceRecord(String staffName, Date absenceDate) {
        this.staffName = staffName;
        this.absenceDate = absenceDate;
        this.caculateDateString = DateHelper.onlyDateFormat.format(absenceDate);
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(Date absenceDate) {
        this.absenceDate = absenceDate;
    }
}
