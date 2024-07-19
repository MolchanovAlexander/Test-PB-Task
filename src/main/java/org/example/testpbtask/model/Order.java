package org.example.testpbtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@SQLDelete(sql = "UPDATE orders SET is_processed = true WHERE id = ?")
@SQLRestriction(value = "is_deleted=false")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal total;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Column(nullable = false)
    private String shippingAddress;
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "order"
//    )
//    private Set<OrderItem> orderItems;
    @Column(nullable = false)
    private boolean isProcessed = false;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }
}