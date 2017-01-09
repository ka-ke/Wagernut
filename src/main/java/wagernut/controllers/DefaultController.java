/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagernut.controllers;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wagernut.domain.*;
import wagernut.services.*;

@Controller
public class DefaultController {

    ShiftService shiftService = new ShiftService();
    WageService wageService;
    HashMap<Integer, Employee> employees;
    ArrayList<Workshift> shiftlist;
    Collection wages;
    boolean breakdown = false;
    int employeeId = -1;
    String employeeName = "";

    @RequestMapping("/")
    public String view(Model model
    ) {
        // Add a file to be parsed and handled into a workshift and employee sets
        shiftService.addShiftFile("files/HourList201403.csv");
        shiftService.parseData();

        shiftlist = shiftService.getShiftlist();
        employees = shiftService.getEmployees();

        // Add employee list if there are employees and a wage list if requested
        if (!employees.isEmpty()) {
            model.addAttribute("employees", employees.values());
        }
        if (employeeId > 0) {
            wages = employees.get(employeeId).getWages();
            employeeName = employees.get(employeeId).getName();
            model.addAttribute("employeeName", employeeName);
            
            // Use the breakdown in model if requested
            if (breakdown) {
                model.addAttribute("breakdown", wages);
            } else {
                model.addAttribute("wages", wages);
            }
        }

        return "index";
    }

    @RequestMapping(value = "/{id}/wage", method = RequestMethod.POST)
    public String wage(Model model, @PathVariable String id) {
        employeeId = Integer.parseInt(id);

        // Count wage for employees
        wageService = new WageService(shiftlist, employees);
        wageService.countWages();
        employees = wageService.getEmployees();

        breakdown = false;

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/breakdown", method = RequestMethod.POST)
    public String breakdown(Model model, @PathVariable String id) {
        employeeId = Integer.parseInt(id);

        // Count wage for employees
        wageService = new WageService(shiftlist, employees);
        wageService.countWages();
        employees = wageService.getEmployees();

        breakdown = true;

        return "redirect:/";
    }
}
