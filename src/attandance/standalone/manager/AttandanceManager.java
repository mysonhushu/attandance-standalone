/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.manager;

import attandance.standalone.bean.AbsenceRecord;
import attandance.standalone.bean.AttandanceDays;
import attandance.standalone.bean.AttandanceRecord;
import attandance.standalone.bean.HandsDay;
import attandance.standalone.bean.LateRecord;
import attandance.standalone.utils.DateHelper;
import attandance.standalone.utils.ExcelUtils;
import com.google.common.collect.Lists;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author huuuuxin
 */
public class AttandanceManager {
    public void processAttandance(String fileName) {
        //1.read data from excel file
        List<AttandanceRecord> attandaceList = ExcelUtils.readDataFromExcel(fileName);
        //2.calculate attandance
        List<AbsenceRecord> absenceList = calculateAbsence(attandaceList);
        List<LateRecord> lateList = calculateLateAttandance(attandaceList);
        //3. write data to execel file
        outputAttandance(lateList, absenceList);
    }
    
    /**
     * 1. get the all work days of employ.
     * 2.  get all the employ name.
     * @param attandaceList 
     */
    private  List<AbsenceRecord> calculateAbsence(List<AttandanceRecord> attandanceList) {
        //1.caculate work days.
        AttandanceDays days = new AttandanceDays();
        days.countHandsDay(attandanceList);
        Map<Date, HandsDay> daysList = days.getCountResult();
        List<Date> workDays = new ArrayList<Date>();
        for (Map.Entry<Date, HandsDay> entry : daysList.entrySet()) {
		System.out.println("Key : " + DateHelper.onlyDateFormat.format(entry.getKey()) + " Value : " + entry.getValue().getHandEmployCount());
                workDays.add(entry.getKey());
	}
        //2.caculate all employs.
        Set<String> employNames = new HashSet<String>();
        for(AttandanceRecord record :  attandanceList) {
            employNames.add(record.getStaffName());
        }
//        System.out.println("total employs :" + employNames.size());
//        System.out.println("total employs list :" + employNames.toString());
        //3. loop each employer. caculate the absence record.
        List<AbsenceRecord> absenceList = Lists.newArrayList();
        for(String staffName : employNames) {
            List<Date> copyWorkDays = Lists.newArrayList(workDays);
            List<Date> recordDates = new ArrayList<Date>();
            for(AttandanceRecord aRecord : attandanceList)
            {
                if(staffName.equals(aRecord.getStaffName())) {
                    recordDates.add(DateHelper.convertOnlyDate(aRecord.getAttandanceDate()));
                }
            }
            copyWorkDays.removeAll(recordDates);
            for(Date absenceDate : copyWorkDays)
            {
                AbsenceRecord ar = new AbsenceRecord(staffName, absenceDate);
                absenceList.add(ar);
            }
        }
        System.out.println(" absence Record:" + absenceList.toString());
        return absenceList;
    }
    
    private List<LateRecord> calculateLateAttandance(List<AttandanceRecord> attandanceList) {
        List<LateRecord> lateList = new ArrayList<LateRecord>();
        for(AttandanceRecord attandance : attandanceList) {
            if(attandance.isLate())
            {
                LateRecord late = new LateRecord(attandance.getStaffName(), attandance.getRecordDate(), attandance.getLateTimeDiffer());
                lateList.add(late);
            }
        }
        return lateList;
    }
    
    private void outputAttandance(List<LateRecord> lates, List<AbsenceRecord> absences) {
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("广力员工考勤统计");
        int width = ((int)(20 * 1.14388)) * 256;
        sheet.setColumnWidth(0, width);
        sheet.setColumnWidth(1, width);
        sheet.setColumnWidth(2, width);
        sheet.setColumnWidth(3, width);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("统计日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("统计状态");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，   
        int i = 0;
        for (; i < lates.size(); i++) {
            row = sheet.createRow((int) i + 1);
            LateRecord lateStaff = lates.get(i);
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(lateStaff.getStaffName());
            row.createCell((short) 1).setCellValue(lateStaff.getCaculateDateString());
            cell = row.createCell((short) 2);
            cell.setCellValue(new SimpleDateFormat(DateHelper.DATE_FORMAT).format(lateStaff.getLateDate()));
            row.createCell((short) 3).setCellValue(lateStaff.getLateTimeDesc());
        }
        for (int j = 0; j < absences.size(); j++) {
            row = sheet.createRow((int) i + j + 1);
            AbsenceRecord absenceStaff = absences.get(j);
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(absenceStaff.getStaffName());
            row.createCell((short) 1).setCellValue(absenceStaff.getCaculateDateString());
            cell = row.createCell((short) 2);
            cell.setCellValue(new SimpleDateFormat(DateHelper.ONLY_DATE_FORMAT).format(absenceStaff.getAbsenceDate()));
            row.createCell((short) 3).setCellValue("未打卡");
        }
        // 第六步，将文件存到指定位置  
        try {
            String fileName = "C:/xhuxing-private/"+new Date(System.currentTimeMillis()).getMonth()+"考勤统计结果.xls";
            FileOutputStream fout = new FileOutputStream(fileName);
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
