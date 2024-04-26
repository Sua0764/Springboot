package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    //제품의 재고가 입력매개변수로 받은 숫자 미만인 제품 정보 얻기
    public List<Product> getProductByInventoryUnder(int num) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(p ->p.getInventory() < num).collect(Collectors.toList());
    }

    // 입력받은 문자열이 포함되어 있는 제품 찾기
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

    // 람다식
    /*public List<Product> getProductByName(String name) {
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

    //제품 재고금액이 높은 상위 ()개 제품
    public List<Product> getProductByInventoryPrice(int limit) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().sorted(Comparator.comparingInt((Product p) -> p.getUnitPrice() * p.getInventory()).reversed())
                .limit(limit).collect(Collectors.toList());
    }

    // 입력매개변수로 받은 ID배열로 제품 찾기
    public List<Product> getProductById(List<Long> idList) {
        List<Product> productList = productRepository.findAll();

        /*List<Product> newProducts = new ArrayList<>();
        for(int i=0; i<productList.size(); i++) {
            for(int j=0; j< idList.size(); j++) {
                if (productList.get(i).getProductId() == idList.get(j)) {
        newProducts.add(productList.get(i));
                }
            }
        }
        return newProducts;*/

        return productList.stream().filter(product -> idList.contains(product.getProductId()))
                .collect(Collectors.toList());
    }
}
