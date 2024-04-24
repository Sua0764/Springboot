package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /*public List<Order> getOrdersAfterDate() {
        List<Order> orders = orderRepository.findAll();

        for (int i=0; i<orders.size(); i++) {
            LocalDate a = LocalDate
            LocalDate b = LocalDate.of(2021, Month.MAY, 1);
            if (a.compareTo(b) > 0) {
                orders.add()
            }
        }

        return
    }*/

    //주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    // 프론트에서 날짜를 정해주는 방식
    public List<Order> getOrderByDateAfter(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(a -> a.getOrderDate().compareTo(date) > 0)
                .collect(Collectors.toList());
    }


    /*public List<Order> getCustomerByOrderDate(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter()
    }*/



}











