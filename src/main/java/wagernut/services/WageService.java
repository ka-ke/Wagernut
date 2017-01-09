/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.services;

import java.util.*;
import wagernut.domain.*;

/**
 *
 * @author Kake
 */
public class WageService {

    ArrayList<Workshift> shiftlist;
    private HashMap<Integer, Employee> employees;

    public WageService(ArrayList shiftlist, HashMap employees) {
        this.shiftlist = shiftlist;
        this.employees = employees;
        countWages();
    }

    /**
     * Counts the wages pertaining all workshifts in shiftlist
     */
    public void countWages() {
        for (Workshift shift : shiftlist) {
            countWageForShift(shift);
        }
    }

    /**
     * Counts the wage for a workshift and adds it to the wage map of the
     * employee
     *
     * @param shift Workshift to be counted wage for
     */
    public void countWageForShift(Workshift shift) {

        Employee employee = getEmployees().get(shift.getEmployeeId());
        
        // If wage for that exact date and time already exists, we don't add it again
        if (employee.getWageByDate(shift.getStart()) != null) {
            return;
        }

        Wage wage = new Wage(shift.getStart());
        double workTime = shift.getWorkTime();

        wage.setRegular(workTime * 3.75);

        if (workTime > 8) {
            wage.setOvertime(countOvertimeCompensation(workTime));
        }

        workTime = shift.getEveningWorkTime();
        wage.setEvening(countEveningCompensation(workTime));

        employee.newWage(wage);
    }

    /**
     * Counts the evening compensation from the evening worktime
     *
     * @param workTime Duration of evening work as double
     * @return Evening work compensation as double
     */
    public double countEveningCompensation(double workTime) {

        return workTime * 1.15;
    }

    /**
     * Counts the overtime compensation from overtime work
     *
     * @param workTime Duration of overtime work as double
     * @return Overtime compensation as double
     */
    public double countOvertimeCompensation(double workTime) {
        double overTimeWage = 0;

        if (workTime > 12) {
            overTimeWage += (workTime - 12) * 3.75;
            workTime = 12;
        }
        if (workTime > 10) {
            overTimeWage += (workTime - 10) * 3.75 * 0.5;
            workTime = 10;
        }
        overTimeWage += (workTime - 8) * 3.75 * 0.25;

        return overTimeWage;
    }

    /**
     * @return the employees
     */
    public HashMap<Integer, Employee> getEmployees() {
        return employees;
    }
}
