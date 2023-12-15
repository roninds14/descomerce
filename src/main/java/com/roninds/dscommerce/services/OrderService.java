package com.roninds.dscommerce.services;

import com.roninds.dscommerce.dto.OrderDTO;
import com.roninds.dscommerce.dto.OrderItemDTO;
import com.roninds.dscommerce.entities.Order;
import com.roninds.dscommerce.entities.OrderItem;
import com.roninds.dscommerce.entities.Product;
import com.roninds.dscommerce.entities.User;
import com.roninds.dscommerce.entities.enums.OrderStatus;
import com.roninds.dscommerce.repositories.OrderItemRepository;
import com.roninds.dscommerce.repositories.OrderRepository;
import com.roninds.dscommerce.repositories.ProductRepository;
import com.roninds.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> result = repository.findById(id);
        Order order = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        authService.validateSelfOrAdmin(order.getClient().getId());

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDTO: orderDTO.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, product.getPrice(), itemDTO.getQuantity());
            order.getItems().add(item);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
