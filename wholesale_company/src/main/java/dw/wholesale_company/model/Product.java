package dw.wholesale_company.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "제품")
public class Product {
    @Id
    @Column(name = "제품번호", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "제품명", nullable = false)
    private String productName;

    @Column(name = "포장단위", nullable = false)
    private String pkgUnit;

    @Column(name = "단가", nullable = false)
    private int unitPrice;

    @Column(name = "재고", nullable = false)
    private int inventory;


}
