package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //고객전체의 평균마일리지보다 마일리지가 큰 고객 정보
    public List<Customer> getCustomerWithHighMileThanAvg() {
        List<Customer> customers = customerRepository.findAll();
        int sum = 0;
        for (int i =0; i < customers.size(); i++) {
            sum = (int) (sum + customers.get(i).getMileage());
        }
        Double avg = (double)sum / (double)customers.size();
        return customers.stream().filter(c->c.getMileage() > avg).collect(Collectors.toList());
    }

    public List<Customer> getCustomerByOrderDate(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        List<Customer> customerList = new ArrayList<>();
        for (int i=0; i < orders.size(); i++) {
            if (orders.get(i).getOrderDate() == date) {
                for (int j=0; j < customers.size(); j++) {
                    if (orders.get(i).getCustomerId().equals(customers.get(j).getCustomerId())) {
                        customerList.add(customers.get(j));
                    }
                }
            }
        }
        return customerList;
    }
}
