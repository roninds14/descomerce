package com.roninds.dscommerce.services;

import com.roninds.dscommerce.dto.OrderDTO;
import com.roninds.dscommerce.entities.Order;
import com.roninds.dscommerce.repositories.OrderRepository;
import com.roninds.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> result = repository.findById(id);
        Order order = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new OrderDTO(order);
    }
}
