package com.roninds.dscommerce.dto;

import com.roninds.dscommerce.entities.Category;
import com.roninds.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @Size(min = 10, message = "Desrição precisa ter no minimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;

    @Positive(message = "O preço deve ser positivo")
    @NotNull(message = "Campo requerido")
    private Double price;

    @Size(max = 255, message = "Não pode conter mais de 255 caracteres")
    private String imgUrl;

    @NotEmpty(message = "Deve conter ao menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(@org.jetbrains.annotations.NotNull Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();

        for(Category cat: product.getCategories()){
            categories.add(new CategoryDTO(cat));
        }
    }
}
