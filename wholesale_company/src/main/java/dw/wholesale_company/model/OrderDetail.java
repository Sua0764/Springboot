package dw.wholesale_company.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "주문세부")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="주문세부번호")
    private long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "주문번호", nullable = false)
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "제품번호", nullable = false)
    private Product productId;

    @Column(name = "단가", nullable = false)
    private long unitPrice;

    @Column(name = "주문수량", nullable = false)
    private long orderQuantity;

    @Column(name = "할인율", nullable = false)
    private float discountRate;



}
