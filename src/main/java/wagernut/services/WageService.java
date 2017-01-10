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

    private ArrayList<Workshift> shiftlist;
    private HashMap<Integer, Employee> employees;
    private double regularWage = 3.75;
    private double overtimeCompensation8 = 0.25;
    private double overtimeCompensation10 = 0.5;
    private double overtimeCompensation12 = 1;
    private double eveningCompensation = 1.15;

    /**
     * @param shiftlist The list of workshifts to count the wages from
     * @param employees Employees that have worked
     */
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

        wage.setRegular(workTime * regularWage);

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

        return workTime * eveningCompensation;
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
            overTimeWage += (workTime - 12) * overtimeCompensation12;
            workTime = 12;
        }
        if (workTime > 10) {
            overTimeWage += (workTime - 10) * 3.75 * overtimeCompensation10;
            workTime = 10;
        }
        overTimeWage += (workTime - 8) * 3.75 * overtimeCompensation8;

        return overTimeWage;
    }

    /**
     * @return Employees with updated wagelists
     */
    public HashMap<Integer, Employee> getEmployees() {
        return employees;
    }
}
