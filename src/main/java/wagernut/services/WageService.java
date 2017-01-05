/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.services;

import java.time.Duration;
import java.util.*;
import wagernut.domain.Wage;
import wagernut.domain.Workshift;

/**
 *
 * @author Kake
 */
public class WageService {

    ArrayList<Workshift> shiftlist;

    public WageService(ArrayList shiftlist) {
        this.shiftlist = shiftlist;
    }

    public double countWageForEmployee(int employeeId) {
        double wage = 0;

        for (Workshift shift : shiftlist) {
            if (shift.getEmployeeId() == employeeId) {
                countWageForShift(shift);
                double shiftWage = shift.getWage().getTotal();
                System.out.println("Vuoron palkka: "+shiftWage);
                wage += shiftWage;
            }
        }

        return wage;
    }

    public void countWageForShift(Workshift shift) {
        Wage wage = new Wage();

//        // ordinary workshift that doesn't last overnight
//        int workHours = shift.getEnd().getHours() - shift.getStart().getHours();
//        double workMinutes = shift.getEnd().getMinutes() - shift.getStart().getMinutes();
//        double workTime = workHours + workMinutes / 60;
//
//        // workshift that lasts overnight
//        if (workTime < 0) {
//            workHours = 24 - shift.getStart().getHours() + shift.getEnd().getHours();
//            workTime = workHours + workMinutes / 60;
//        }

        double workTime = shift.getWorkTime();

        wage.setRegular(workTime * 3.75);
                
        if(workTime > 8){
            wage.setOvertime(countOvertimeCompensation(workTime));
        }
           
        wage.setEvening(countEveningCompensation(shift.getEveningWork()));

//        System.out.println("Start: "+shift.getStart());
//        System.out.println("End: "+shift.getEnd());
//        System.out.println("Worked: "+workTime);
//        System.out.println("");
        shift.setWage(wage);
    }

    public double countEveningCompensation(double workTime) {
        
        return workTime*1.15;
    }

    public double countOvertimeCompensation(double workTime) {
        double overTimeWage = 0;
        
        if(workTime>12){
            overTimeWage += (workTime-12)*3.75;
            workTime = 12;
        }
        if(workTime>10){
            overTimeWage += (workTime-10)*3.75*0.5;
            workTime = 10;        
        }
        overTimeWage += (workTime-8)*3.75*0.25;
        
        return overTimeWage;
    }
}
