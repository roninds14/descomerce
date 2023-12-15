package com.roninds.dscommerce.repositories;

import com.roninds.dscommerce.entities.OrderItem;
import com.roninds.dscommerce.entities.primarykeys.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
