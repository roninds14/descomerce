package com.roninds.dscommerce.dto;

import com.roninds.dscommerce.entities.User;
import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Date birthDate;

    private List<String> roles = new ArrayList<>();

    public UserDTO(@NotNull User entinty) {
        this.id = entinty.getId();
        this.name = entinty.getName();
        this.email = entinty.getEmail();
        this.phone = entinty.getPhone();
        this.birthDate = entinty.getBirthDate();

        for(GrantedAuthority role : entinty.getRoles()){
            roles.add(role.getAuthority());
        }
    }
}
