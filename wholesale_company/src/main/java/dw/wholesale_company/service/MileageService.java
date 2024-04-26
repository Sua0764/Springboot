package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.MileageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MileageService {
    @Autowired
    MileageRepository mileageRepository;

    public List<Mileage> getAllMileages() {
        return mileageRepository.findAll();
    }


}
