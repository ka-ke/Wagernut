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
public class Employee {

    private String name;
    private int id;
    private ArrayList<Wage> wages;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
        wages = new ArrayList();
    }

    public void newWage(Wage wage) {
        wages.add(wage);
    }

    public Wage getWageByDate(Date date){
        for(Wage wage : wages){
            if(wage.getDate().equals(date)){
                return wage;
            }
        }
        return null;
    }

    public double getTotalWage() {
        double totalWage = 0;
        for (Wage wage : wages) {
            totalWage += wage.getTotal();
        }
        return totalWage;
    }

    public List getWages() {
        return wages;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
