/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.manager;

import attandance.standalone.bean.AttandanceRecord;
import attandance.standalone.utils.ExcelUtils;
import java.util.List;

/**
 *
 * @author huuuuxin
 */
public class AttandanceManagerTest {
    public static void main(String args[]) {
        AttandanceManager a = new AttandanceManager();
         a.processAttandance("C:\\xhuxing-private\\2017_02.xls");
    }
}
