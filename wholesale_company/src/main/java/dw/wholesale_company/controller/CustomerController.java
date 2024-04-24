package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/highmilethanavg")
    public ResponseEntity<List<Customer>> getCustomerWithHighMileThanAvg() {
        return new ResponseEntity<>(customerService.getCustomerWithHighMileThanAvg(),
                HttpStatus.OK);
    }

    @GetMapping("/customers/order/{date}")
    public ResponseEntity<List<Customer>> getCustomerByOrderDate(LocalDate date) {
        return new ResponseEntity<>(customerService.getCustomerByOrderDate(date),
                HttpStatus.OK);
    }

}
