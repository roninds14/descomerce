package com.roninds.dscommerce.entities;

import com.roninds.dscommerce.entities.primarykeys.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Double price;
    private Integer quantity;

    public OrderItem(Order order, Product product, Double price, Integer quantity) {
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        this.id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }
}

