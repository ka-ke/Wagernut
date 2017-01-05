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

    private int id;
    private String name;
    private Map<Date, Double> salaries;

    public Employee(String name) {
        this.name = name;
        salaries = new HashMap();
    }

    public void newSalary(Date date, double salary) {
        salaries.put(date, salary);
    }

    public double getSalary(int month) {
        return 0;
    }

    public double getSalary(Date date) {
        return 0;
    }
}
