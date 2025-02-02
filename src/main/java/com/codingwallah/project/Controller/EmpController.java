package com.codingwallah.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.codingwallah.project.Model.Employee;
import com.codingwallah.project.Service.EmpService;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private EmpService empServices;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empServices.readEmployee();
    }
    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        System.out.println("Received Employee: " + employee);
        return empServices.createEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (empServices.deleteEmployee(id)) {
            return "Deleted successfully";
        }
        return "Cannot delete";
    }
    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        return empServices.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return empServices.readEmployeebyid(id);
    }
}