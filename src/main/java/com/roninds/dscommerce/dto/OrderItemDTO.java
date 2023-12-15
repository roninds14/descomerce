package com.roninds.dscommerce.dto;

import com.roninds.dscommerce.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public OrderItemDTO(OrderItem entity) {
        this.productId = entity.getProduct().getId();
        this.name = entity.getProduct().getName();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
        this.imgUrl = entity.getProduct().getImgUrl();
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
