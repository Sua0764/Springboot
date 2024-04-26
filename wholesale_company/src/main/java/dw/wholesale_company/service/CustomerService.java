package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MileageRepository mileageRepository;

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

    // 입력받은 마일리지 등급별로 고객 수
    public int getCustomerByMileageGrade(String grade) {
        List<Mileage> mileages = mileageRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        List<Customer> gradeCustomer = new ArrayList<>();

        int num = 0;
        for (int i=0; i < mileages.size(); i++) {
            if (mileages.get(i).getMileageGrade().equalsIgnoreCase(grade)) {
                long lowLimit = mileages.get(i).getLowLimit();
                long highLimit = mileages.get(i).getHighLimit();

                for (int j=0; j<customers.size(); j++) {
                    if (customers.get(j).getMileage() >= lowLimit && customers.get(j).getMileage() <= highLimit ) {
                        gradeCustomer.add(customers.get(j));
                        num++;
                    }
                }

            }
        }

        return num;

        // 선생님 풀이 (람다식) : 입력받은 마일리지 등급의 모든 고객 정보
        /*Optional<Mileage> mileageOptional = mileageRepository.findById(grade);
        if (mileageOptional.isEmpty()) {
            throw new ResourceNotFoundException("Mileage", "Grade", grade);
        }
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().filter(customer -> customer.getMileage() >= mileageOptional.get().getLowLimit()
                && customer.getMileage() <= mileageOptional.get().getHighLimit()).collect(Collectors.toList());*/
    }
}
