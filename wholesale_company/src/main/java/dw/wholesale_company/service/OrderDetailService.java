package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.controller.OrderDetailController;
import dw.wholesale_company.model.OrderDetail;
import dw.wholesale_company.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }
}
