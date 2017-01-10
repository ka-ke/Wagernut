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
import wagernut.domain.*;
import wagernut.services.*;

@Controller
public class DefaultController {

    ShiftService shiftService = new ShiftService();
    WageService wageService;
    HashMap<Integer, Employee> employees = new HashMap();
    Collection wages;
    String wageFormat = "";
    int employeeId = 0;

    @RequestMapping("/")
    public String view(Model model
    ) {
        // Add some necessary modelattributes
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("wageFormat", wageFormat);
        // Add employee list if there are employees
        if (!employees.isEmpty()) {
            model.addAttribute("employees", employees.values());
        }
        // Add wages of the requested employee
        if (employeeId > 0) {
            Employee employee = employees.get(employeeId);
            wages = employee.getWages();
            model.addAttribute("employee", employee);
            model.addAttribute("total", employee.getTotalWage());
            model.addAttribute("wages", wages);
        }
        return "index";
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public String countWages() {
        // Add a file to be parsed and handled into a workshift and employee sets
        shiftService.addShiftFile("files/HourList201403.csv");
        shiftService.parseData();
        ArrayList<Workshift> shiftlist = shiftService.getShiftlist();
        employees = shiftService.getEmployees();

        // Count wage for employees
        wageService = new WageService(shiftlist, employees);
        wageService.countWages();
        employees = wageService.getEmployees();

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/wagelist", method = RequestMethod.POST)
    public String wage(Model model, @PathVariable String id) {
        // Set the employeeId to see the wages of corresponding employee
        employeeId = Integer.parseInt(id);
        wageFormat = "wagelist";

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/breakdown", method = RequestMethod.POST)
    public String breakdown(Model model, @PathVariable String id) {
        // Set the employeeId to see the wagebreakdown of corresponding employee
        employeeId = Integer.parseInt(id);
        wageFormat = "breakdown";

        return "redirect:/";
    }
}
