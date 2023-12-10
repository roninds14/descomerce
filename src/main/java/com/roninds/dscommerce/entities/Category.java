package com.roninds.dscommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "products")
@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    @Setter(AccessLevel.NONE)
    private Set<Product> products = new HashSet<Product>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
