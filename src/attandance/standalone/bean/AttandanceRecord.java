package attandance.standalone.bean;

import attandance.standalone.utils.DateHelper;
import java.util.Calendar;
import java.util.Date;

public class AttandanceRecord {

    private String departmentName;
    private String staffName;
    private int recordCode;
    private Date recordDate;
    private String recordStatus;
    private int machineCode;
    private String recordNumber;
    private String differType;
    private String cardNumber;
    private boolean isBeLate = false;
    private boolean isAbsence = false;

    @Override
    public String toString() {
        return "AttandanceRecord{" + "departmentName=" + departmentName + ", staffName=" + staffName + ", recordCode=" + recordCode + ", recordDate=" + DateHelper.dateFormat.format(recordDate) + ", recordStatus=" + recordStatus + ", machineCode=" + machineCode + ", recordNumber=" + recordNumber + ", differType=" + differType + ", cardNumber=" + cardNumber + ", isBeLate=" + isBeLate + ", isAbsence=" + isAbsence + '}';
    }

    public void setAttribute(int index, String value){
        switch(index) {
            case 1: this.setDepartmentName(value);break;
            case 2: this.setStaffName(value);break;
            case 3: this.setRecordCode(Integer.valueOf(value));break;
            case 4: this.setRecordDate(DateHelper.convert(value));break;
            case 5: this.setRecordStatus(value);break;
            case 6: this.setMachineCode(Integer.valueOf(value));break;
            case 7: this.setRecordNumber(value);break;
            case 8: this.setDifferType(value);break;
            case 9: this.setCardNumber(value);break;
            default:
                //do nothing;
        }
    }
        
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(int recordCode) {
        this.recordCode = recordCode;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public int getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(int machineCode) {
        this.machineCode = machineCode;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getDifferType() {
        return differType;
    }

    public void setDifferType(String differType) {
        this.differType = differType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isIsBeLate() {
        return isBeLate;
    }

    public void setIsBeLate(boolean isBeLate) {
        this.isBeLate = isBeLate;
    }

    public boolean isIsAbsence() {
        return isAbsence;
    }

    public void setIsAbsence(boolean isAbsence) {
        this.isAbsence = isAbsence;
    }
    
    /**
     * 
     * @return date like 2017-02-03
     */
    public String getAttandanceDate() {
        return DateHelper.onlyDateFormat.format(this.recordDate);
    }
    
    public boolean isLate() {
       Calendar cal = Calendar.getInstance();
       cal.setTime(this.recordDate);
       Calendar onTime = Calendar.getInstance();
       onTime.setTime(DateHelper.convertOnlyDate(this.getAttandanceDate()));
       onTime.set(Calendar.HOUR_OF_DAY,  8);
       onTime.set(Calendar.MINUTE,  40);
       Date a = onTime.getTime();
//       System.out.println("考勤时间："+ DateHelper.toDateString(a));
//       System.out.println("打卡时间："+ DateHelper.toDateString(recordDate));
       if(this.recordDate.getTime() > a.getTime()) {
//           System.out.println("迟到！");
           return true;
       } else {
           return false;
       }
    }
    
    public String getLateTimeDiffer() {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数     
        long nh = 1000 * 60 * 60;// 一小时的毫秒数     
        long nm = 1000 * 60;// 一分钟的毫秒数     
        Calendar cal = Calendar.getInstance();
       cal.setTime(this.recordDate);
       Calendar onTime = Calendar.getInstance();
       onTime.setTime(DateHelper.convertOnlyDate(this.getAttandanceDate()));
       onTime.set(Calendar.HOUR_OF_DAY,  8);
       onTime.set(Calendar.MINUTE,  30);
       Date a = onTime.getTime();
        System.out.println("员工姓名："+ this.getStaffName());
       System.out.println("考勤时间："+ DateHelper.toDateString(a));
       System.out.println("打卡时间："+ DateHelper.toDateString(recordDate));
       long diff = recordDate.getTime() - a.getTime(); 
       long day  = diff / nd;// 计算差多少天     
       if(this.recordDate.getTime() > a.getTime()) {
//           System.out.println("迟到！");
           System.out.println("迟到："+(diff % nd % nh / nm + day * 24 * 60) +" 分钟");
           return "迟到："+(diff % nd % nh / nm + day * 24 * 60) +" 分钟";// 计算差多少分钟;
       } else {
           System.out.println("早到："+(diff % nd % nh / nm + day * 24 * 60) +" 分钟");
           return"早到："+(diff % nd % nh / nm + day * 24 * 60) +" 分钟";// 计算差多少分钟;
       }
    }
}
