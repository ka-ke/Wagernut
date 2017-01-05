/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.domain;

/**
 *
 * @author Kake
 */
public class Wage {
    
    private double regular;
    private double evening;
    private double overtime;
    
    public Wage(){
        regular = 0;
        evening = 0;
        overtime = 0;
    }

    /**
     * @return the regular
     */
    public double getRegular() {
        return regular;
    }

    /**
     * @param regular the regular to set
     */
    public void setRegular(double regular) {
        this.regular = regular;
    }

    /**
     * @return the evening
     */
    public double getEvening() {
        return evening;
    }

    /**
     * @param evening the evening to set
     */
    public void setEvening(double evening) {
        this.evening = evening;
    }

    /**
     * @return the overtime
     */
    public double getOvertime() {
        return overtime;
    }

    /**
     * @param overtime the overtime to set
     */
    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }
    
    public double getTotal(){
        return regular+evening+overtime;
    }
    
    
}
