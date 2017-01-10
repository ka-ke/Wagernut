/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import wagernut.domain.Employee;
import wagernut.domain.Workshift;

/**
 *
 * @author Kake
 */
public class DataHandler {

    ArrayList shiftlist;
    HashMap employees;

    public DataHandler() {
        shiftlist = new ArrayList<Workshift>();
        employees = new HashMap<Integer, Employee>();
    }

    /**
     * Uses the datalines to create workshift and employee objects
     * @param data A dataline to be handled
     */
    public void handleData(String data) {
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            String employeeName = scanner.next();
            String idString = scanner.next();
            String date = scanner.next();
            String start = date + " " + scanner.next();
            String end = date + " " + scanner.next();

            if (idString.equals("Person ID")) {
                continue;
            }

            int employeeId = handleInt(idString);
            Date startDate = handleDate(start);
            Date endDate = handleDate(end);

            if (employeeId == -1 || startDate == null || endDate == null) {
                continue;
            }

            if (!employees.containsKey(employeeId)) {
                employees.put(employeeId, new Employee(employeeName, employeeId));
            }
            Workshift newShift = new Workshift(employeeId, startDate, endDate);

            shiftlist.add(newShift);
        }
    }

    /**
     * Handles the integer part of the data
     *
     * @param idString Integer data as a string
     * @return Employee ID as integer
     */
    private int handleInt(String idString) {
        int employeeId = -1;

        try {
            employeeId = Integer.parseInt(idString);
        } catch (Exception ex) {
            System.out.println("Bad intformat");
        }

        return employeeId;
    }

    /**
     * Handles the date part of the data
     *
     * @param dateString Date data as a string
     * @return Date information pertaining the data
     */
    public Date handleDate(String dateString) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        Date date = null;

        try {
            date = format.parse(dateString);
        } catch (ParseException ex) {
            System.out.println("Bad dateformat");
        }

        return date;
    }
}
