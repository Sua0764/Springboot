package dw.wholesale_company.repository;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, String> {
    /*@Query("select o1 from Order o1 where o1.orderDate >= '2021-05-01")
    Order getOrdersAfterDate;*/
}
