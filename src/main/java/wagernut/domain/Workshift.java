/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.domain;

import java.util.*;

/**
 *
 * @author Kake
 */
public class Workshift {

    private int employeeId;
    private Date start;
    private Date end;
    private Wage wage;

    public Workshift(int id, Date start, Date end) {
        employeeId = id;
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public double getWorkTime() {
        // workshift that doesn't last overnight
        int workHours = end.getHours() - start.getHours();
        double workMinutes = end.getMinutes() - start.getMinutes();
        double workTime = workHours + workMinutes / 60;

        // workshift that lasts overnight
        if (workTime < 0) {
            workHours = 24 - start.getHours() + end.getHours();
            workTime = workHours + workMinutes / 60;
        }
        return workTime;
    }

    public double getEveningWork() {
        double workTime = 0;
        int startingHour = start.getHours();
        int endingHour = end.getHours();
        double startingMin = start.getMinutes();
        double endingMin = end.getMinutes();

        // work that doesn't last overnight
        if (startingHour < endingHour) {
            // evening hours between 0-6 o'clock
            if (startingHour < 6) {
                workTime += 6 - startingHour - startingMin / 60;

                if (endingHour < 6) {
                    workTime -= 6 - endingHour ;
                }
            }
            // evening hours between 18-24 o'clock
            if (endingHour > 18 && endingHour < 24) {
                workTime += endingHour - 18;

                if (startingHour > 18) {
                    workTime -= startingHour - 18;
                }
            }
        }

        // work that lasts overnight
        if (startingHour > endingHour) {
            //evening hours between 18-24 o'clock
            workTime += 6;
            if (startingHour > 18) {
                workTime -= startingHour - 18;

                if (endingHour > 18) {
                    workTime += endingHour - 18;
                }
            }

            // evening hours between 0-6 o'clock
            workTime += 6;
            if (endingHour < 6) {
                workTime -= 6 - endingHour;

                if (startingHour < 6) {
                    workTime += 6 - startingHour;
                }
            }
        }

        return workTime;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setWage(Wage wage) {
        this.wage = wage;
    }

    public Wage getWage() {
        return wage;
    }
}
