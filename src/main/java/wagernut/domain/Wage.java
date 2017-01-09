/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.domain;

import java.util.Date;

/**
 *
 * @author Kake
 */
public class Wage {

    private double regular;
    private double evening;
    private double overtime;
    private double total;
    private Date date;

    public Wage(Date date) {
        regular = 0;
        evening = 0;
        overtime = 0;
        total = 0;
        this.date = date;
    }

    /**
     * @return the regular
     */
    public double getRegular() {
        return regular;
    }

    /**
     * Sets the regular and adjusts the total wage
     * @param regular the regular to set
     */
    public void setRegular(double regular) {
        total += regular - this.regular;
        this.regular = regular;
    }

    /**
     * @return the evening
     */
    public double getEvening() {
        return evening;
    }

    /**
     * Sets the evening and adjusts the total wage
     * @param evening the evening to set
     */
    public void setEvening(double evening) {
        total += evening - this.evening;
        this.evening = evening;
    }

    /**
     * @return the overtime
     */
    public double getOvertime() {
        return overtime;
    }

    /**
     * Sets the overtime and adjusts the total wage
     * @param overtime the overtime to set
     */
    public void setOvertime(double overtime) {
        total += overtime - this.overtime;
        this.overtime = overtime;
    }

    /**
     * @return the total wage
     */
    public double getTotal() {
        return total;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
}
