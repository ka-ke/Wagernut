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
    
    Scanner scanner;
    DataHandler handler = new DataHandler();
    
    public ShiftService(String filename) {
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    
    public void parseData(){

        while(scanner.hasNextLine()){
            String dataLine = scanner.nextLine();
            handler.handleData(dataLine);
        }
    }
        
    public ArrayList getShiftlist(){
        return handler.shiftlist;
    }
    
    public HashMap getEmployees(){
        return handler.employees;
    }
}
