package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK) ;
    }

    /*@GetMapping("/products/lessinventory")
    public ResponseEntity<List<Product>> getLessInventoryProduct() {
        return new ResponseEntity<>(productService.getLessInventoryProduct(),HttpStatus.OK);
    }*/

    @GetMapping("/products/inventory/under/{num}")
    public ResponseEntity<List<Product>> getProductByInventoryUnder(@PathVariable int num) {
        return new ResponseEntity<>(productService.getProductByInventoryUnder(num),
                HttpStatus.OK);
    }

    @GetMapping("products/contain/{name}")
    public ResponseEntity<List<Product>> getProductWithName(@PathVariable String name) {
        return new ResponseEntity<>(productService.getProductWithName(name),
                HttpStatus.OK);
    }

    @GetMapping("products/between/{lowLimit}/{highLimit}")
    public ResponseEntity<List<Product>> getProductBetweenPrice(@PathVariable int lowLimit, int highLimit) {
        return new ResponseEntity<>(productService.getProductBetweenPrice(lowLimit,highLimit),
                HttpStatus.OK);
    }

    @GetMapping("products/idarray")
    public ResponseEntity<List<Product>> getProductById(@RequestBody List<Long> idList) {
        return new ResponseEntity<>(productService.getProductById(idList),HttpStatus.OK);
    }

    @GetMapping("/products/inventoryprice/{limit}")
    public ResponseEntity<List<Product>> getProductByInventoryPrice(@PathVariable int limit) {
        return new ResponseEntity<>(productService.getProductByInventoryPrice(limit),
                HttpStatus.OK);
    }
}

