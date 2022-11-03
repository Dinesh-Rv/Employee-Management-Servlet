package com.ideas2it.controller;

import com.ideas2it.model.Employee;
import com.ideas2it.model.LeaveRecords;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.LeaveRecordsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
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
    private final LeaveRecordsService leaveRecordsService;

    public EmployeeController(EmployeeService employeeService, LeaveRecordsService leaveRecordsService) {
        this.employeeService = employeeService;
        this.leaveRecordsService = leaveRecordsService;
    }

    /**
     * <p>
     *     Adds an new employee
     * </p>
     * @param employee
     *        has an employee object
     * @param model
     *
     * @return
     */
    @PostMapping(value = "/addEmployee")
    public String addEmployee(Employee employee, Model model) {
       model.addAttribute("message", employeeService.addEmployee(employee));
       return "createEmployee";
    }

    @GetMapping(value = "/readAllEmployees")
    public String getEmployees(ModelMap modelMap) {
        modelMap.addAttribute("employees", employeeService.getEmployees());
        return "readAllEmployees";
    }

    @GetMapping(value = "/ManageEmployee")
    public String getEmployeeById(@RequestParam("employeeId") String employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "modifyEmployee";
    }

    @RequestMapping(value = "/editEmployee/{employeeId}")
    public String editEmployee(@PathVariable String employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "updateEmployee";
    }

    @RequestMapping(value = "leaveRecords/{employeeId}")
    public String leaveRecordsPage(@PathVariable String employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "leaveRecords";
    }

    @RequestMapping(value = "leaveRecords/readAllLeaveRecords/{employeeId}")
    public String getAllLeaveRecord(@PathVariable String employeeId, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        redirectAttributes.addFlashAttribute("message", leaveRecordsService.getLeaveRecords(employee));
        return "redirect:/manageEmployee";
    }

    @RequestMapping(value = "editEmployee/{employeeId}/UpdateEmployee", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", employeeService.updateEmployee(employee));
        return "redirect:/employee";
    }

    @RequestMapping(value = "deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        redirectAttributes.addFlashAttribute("message", employeeService.removeEmployee(employee));
        return "redirect:/employee";
    }

    @RequestMapping(value = "leaveRecords/CreateLeaveRecord/{employeeId}")
    public String leaveRecordForm(@PathVariable String employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "createLeaveRecord";
    }

    @PostMapping(value = "/leaveRecords/CreateLeaveRecord/AddLeaveRecord")
    public String addLeaveRecord(Employee employee, LeaveRecords leaveRecord, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", leaveRecordsService.addLeaveRecord(leaveRecord, employee));
        return "redirect:/manageEmployee";
    }

    @RequestMapping(value = "leaveRecords/selectLeaveRecord/{employeeId}")
    public String selectLeaveRecord(@PathVariable String employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<LeaveRecords> leaveRecords = leaveRecordsService.getLeaveRecords(employee);
        Iterator<LeaveRecords> iterator = leaveRecords.iterator();

        while (iterator.hasNext()) {
            for (int i = 0; i<leaveRecords.size(); i++) {
                String recordName = "leaveRecord" + i;
                model.addAttribute(recordName, iterator.next());
            }
        }
        return "selectLeaveRecord";
    }

    @RequestMapping(value = "leaveRecords/selectLeaveRecord/GetLeaveRecordById")
    public String getLeaveRecord(String leaveRecordId, Model model) {
        String nextPage;
        LeaveRecords leaveRecord = leaveRecordsService.getLeaveRecordById(leaveRecordId);
        if(leaveRecord != null) {
            model.addAttribute("leaveRecord", leaveRecordsService.getLeaveRecordById(leaveRecordId));
            nextPage = "updateLeaveRecord";
        } else {
            model.addAttribute("message", "An Error Occurred");
            nextPage = "manageEmployee";
        }
        return nextPage;
    }

    @PostMapping(value = "leaveRecords/selectLeaveRecord/UpdateLeaveRecord")
    public String updateLeaveRecord(LeaveRecords leaveRecord, RedirectAttributes redirectAttributes) {
        System.out.println(leaveRecord);
        redirectAttributes.addFlashAttribute("message", leaveRecordsService.updateLeaveRecords(leaveRecord));
        return "redirect:/manageEmployee";
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
        return "readAllEmployees";
    }

    @RequestMapping(value = "/projects")
    public String projectPage() {
        return "projects";
    }

    @RequestMapping(value = "/createEmployee")
    public String createEmployee() {
        return "createEmployee";
    }

    @RequestMapping(value = "/manageEmployee")
    public String manageEmployee() {
        return "manageEmployee";
    }

    @RequestMapping(value = "/modifyEmployee")
    public String modifyEmployeePage() {
        return "modifyEmployee";
    }
}
