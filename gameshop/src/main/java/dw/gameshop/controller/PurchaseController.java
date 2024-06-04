package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping("/products/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    @PostMapping("/products/purchaselist")
    public List<Purchase> savePurchaseList(@RequestBody List<Purchase> purchaseList) {
        return purchaseService.savePurchaseList(purchaseList);
    }

    @GetMapping("/products/purchase")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/products/purchase/id/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Purchase> getPurchaseListById(@PathVariable String userId) {
        return purchaseService.getPurchaseListByUser(userId);
    }

    @GetMapping("/products/purchase/name/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Purchase>> getPurchaseByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(purchaseService.getPurchaseByUserName(userName),(HttpStatus.OK));
    }

    @GetMapping("/products/purchase/current")
    public List<Purchase> getPurchaseListByCurrentUser() {
        return purchaseService.getPurchaseListByCurrentUser();
    }
}
