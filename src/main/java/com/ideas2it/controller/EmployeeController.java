package com.ideas2it.controller;

import com.ideas2it.model.Employee;
import com.ideas2it.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Controller for Employee Operations in an office
 * </p>
 *
 * @author Dinesh Kumar R
 * @since 2022/10/21
 *
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/addEmployee")
    public String addEmployee(Employee employee, ModelMap modelMap) {
       modelMap.addAttribute("message", employeeService.addEmployee(employee));
       return "createEmployee";
    }

    @GetMapping(value = "/ReadAllEmployees")
    public String getEmployees(ModelMap modelMap) {
        modelMap.addAttribute("employees", employeeService.getEmployees());
        return "readEmployee";
    }

    @GetMapping(value = "/ReadEmployeeById")
    public String getEmployeeById(String employeeId, ModelMap modelMap) {
        modelMap.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "readEmployeeById";
    }
    @RequestMapping
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value="/employee")
    public String employeeCrud() {
        return "employee";
    }

    @RequestMapping(value = "/readEmployee")
    public String readEmployee() {
        return "readEmployee";
    }

    @RequestMapping(value = "/projects")
    public String projectCrud() {
        return "projects";
    }

    @RequestMapping(value = "/createEmployee")
    public String createEmployee() {
        return "createEmployee";
    }

    @RequestMapping(value = "/readEmployeeById")
    public String readEmployeeByIdPage() {
        return "readEmployeeById";
    }
}
