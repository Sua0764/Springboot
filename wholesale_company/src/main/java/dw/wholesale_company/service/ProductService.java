package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /*public List<Product> getLessInventoryProduct() {
        List<Product> productList = productRepository.findAll();

        Product productInv = productList.get(0);
        for (int i = 0; i < productList.size(); i++ ) {
            if (productInv.getInventory() > 50) {
                productInv = productList.get(i+1);
            }
        }
        return
    }*/

    //제품의 재고가 50개 미만인 제품 정보 얻기
    public List<Product> getProductByInventoryUnder(int num) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(p ->p.getInventory() < num).collect(Collectors.toList());
    }

    public List<Product> getProductWithName(String name) {
        List<Product> productList = productRepository.findAll();

        List<Product> productWithName = new ArrayList<>();
        for (int i=0; i<productList.size(); i++) {
            if (productList.get(i).getProductName().contains(name)) {
                productList.get(i);
                productWithName.add(productList.get(i));
            }
        }
        return productWithName;
    }

    /*public List<Product> getProductByName2(String name) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(p ->p.getProductName().contains(name)).collect(Collectors.toList());
    }*/

    public List<Product> getProductBetweenPrice(int lowLimit, int highLimit) {
        List<Product> productList = productRepository.findAll();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            int price = productList.get(i).getUnitPrice();
            if (price > lowLimit && price < highLimit) {
                products.add(productList.get(i));
            }
        }
        return products;
    }
}
