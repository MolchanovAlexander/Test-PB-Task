package org.example.testpbtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer amount;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private boolean isProcessed = false;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }
}