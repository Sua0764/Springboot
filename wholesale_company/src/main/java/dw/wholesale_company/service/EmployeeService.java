package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    // 가장 최근 입사한 사원 정보

    public Employee getRecentEmployee() {
        List<Employee> employees = employeeRepository.findAll();

        Employee recent = employees.get(0);
        for (int i = 0; i < employees.size(); i++ ) {
            if (recent.getHireDate().isAfter(employees.get(i+1).getHireDate())) {
                recent = employees.get(i);
            }
        }
        return recent;
    }
    public Employee getEmployeeByHireLatest() {
        return employeeRepository.findAll().stream().
                filter(e->e.getPosition().equals("사원"))
                .sorted(Comparator.comparing(Employee::getHireDate).reversed())
                .findFirst().get();
    }

}
