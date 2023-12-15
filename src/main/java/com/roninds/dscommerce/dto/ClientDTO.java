package com.roninds.dscommerce.dto;

import com.roninds.dscommerce.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;

    public ClientDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
