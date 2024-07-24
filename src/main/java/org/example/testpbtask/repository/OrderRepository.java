package org.example.testpbtask.repository;

import java.util.List;
import org.example.testpbtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.isProcessed = false")
    List<Order> findAllByProcessedNot();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.isProcessed = true WHERE o.id IN :orderIds")
    void markOrdersAsProcessed(List<Long> orderIds);
}
