package org.example.testpbtask.repository;

import org.example.testpbtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
