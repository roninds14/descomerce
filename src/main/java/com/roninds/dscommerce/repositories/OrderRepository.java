package com.roninds.dscommerce.repositories;

import com.roninds.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{}
