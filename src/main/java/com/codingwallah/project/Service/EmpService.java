package com.codingwallah.project.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingwallah.project.Model.Employee;

@Service
public interface EmpService {
    String createEmployee(Employee newEmployee);
    List<Employee> readEmployee();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id,Employee employee);
    Employee readEmployeebyid(Long id);
}
