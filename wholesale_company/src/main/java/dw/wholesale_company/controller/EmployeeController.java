package dw.wholesale_company.controller;

import dw.wholesale_company.model.Employee;
import dw.wholesale_company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/recentemployee")
    public ResponseEntity<Employee> getRecentEmployee() {
        return new ResponseEntity<>(employeeService.getRecentEmployee(),HttpStatus.OK);
    }

    @GetMapping("/employees/hiredate/latest")
    public ResponseEntity<Employee> getEmployeeByHireLatest() {
        return new ResponseEntity<>(employeeService.getEmployeeByHireLatest(),HttpStatus.OK);
    }
}
