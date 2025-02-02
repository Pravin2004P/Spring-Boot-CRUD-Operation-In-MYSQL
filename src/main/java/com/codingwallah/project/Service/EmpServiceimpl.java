package com.codingwallah.project.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingwallah.project.Entity.EmployeeEntity;
import com.codingwallah.project.Model.Employee;
import com.codingwallah.project.Repository.EmployeeRepository;

@Service
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmployeeRepository employeeRepository;
    //private List<Employee> employeeList = new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        //employeeList.add(newEmployee);
        return "saved successfully";
    }

    @Override
    public List<Employee> readEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employeeList1 = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setEmail(employeeEntity.getEmail());
            emp.setName(employeeEntity.getName());
            employeeList1.add(emp);
        }
        return employeeList1;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        Iterator<EmployeeEntity> iterator = employeeEntities.iterator();
        while (iterator.hasNext()) {
            EmployeeEntity employeeEntity = iterator.next();
            if (employeeEntity.getId().equals(id)) {
                employeeRepository.delete(employeeEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        Iterator<EmployeeEntity> iterator = employeeEntities.iterator();
        while (iterator.hasNext()) {
            EmployeeEntity employeeEntity = iterator.next();
            if (employeeEntity.getId().equals(id)) {
                employeeEntity.setName(employee.getName());
                employeeEntity.setEmail(employee.getEmail());
                employeeRepository.save(employeeEntity);
                return "updated successfully";
            }
        }
        return "Employee not found";
    }

    @Override
    public Employee readEmployeebyid(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        Employee emp = new Employee();
        emp.setId(employeeEntity.getId());
        emp.setEmail(employeeEntity.getEmail());
        emp.setName(employeeEntity.getName());
        return emp;
    }
}