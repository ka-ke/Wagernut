/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Kake
 */
public class ShiftService {

    ArrayList<File> files;
    DataHandler handler = new DataHandler();

    public ShiftService() {
        files = new ArrayList();
    }

    /**
     * Adds a new datafile to the list of workshifts
     *
     * @param filename Name of the file to be added
     */
    public void addShiftFile(String filename) {
        try {
            File file = new File(filename);
            files.add(file);
        } catch (Exception ex) {
            System.out.println("File not found");
        }
    }

    /**
     * Parses the datafiles into datalines to be handled by Datahandler
     */
    public void parseData() {

        for (File file : files) {
            Scanner scanner;
            try {
                scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String dataLine = scanner.nextLine();
                    handler.handleData(dataLine);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("No good files to parse");
            }
        }
    }

    /**
     * @return The list with all workshifts
     */
    public ArrayList getShiftlist() {
        return handler.shiftlist;
    }

    /**
     * @return The map with all employees
     */
    public HashMap getEmployees() {
        return handler.employees;
    }
}
