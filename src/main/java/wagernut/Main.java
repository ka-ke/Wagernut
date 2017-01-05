/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut;

import java.util.*;
import wagernut.domain.Workshift;
import wagernut.services.ShiftService;
import wagernut.services.WageService;

/**
 *
 * @author Kake
 */
public class Main {
    public static void main(String[] args) {
        
        ShiftService shiftService = new ShiftService("files/HourList201403.csv");
        shiftService.parseData();
        ArrayList<Workshift> shiftlist = shiftService.getShiftlist();
        HashMap<Integer, String> employees = shiftService.getEmployees();
        
        WageService wageService = new WageService(shiftlist);
        double wage = wageService.countWageForEmployee(3);
        System.out.println(employees.get(3)+" palkka: "+wage);
        
        System.out.println("closing");
    }
}
