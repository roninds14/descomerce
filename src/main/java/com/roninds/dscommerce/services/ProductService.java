package com.roninds.dscommerce.services;

import com.roninds.dscommerce.dto.ProductDTO;
import com.roninds.dscommerce.entities.Product;
import com.roninds.dscommerce.repositories.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);

        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();

        copyDtoToEntity(productDTO, product);

        Product productSaved = repository.save(product);

        return new ProductDTO(productSaved);
    }

    @Transactional
    public  ProductDTO update(ProductDTO productDTO, Long id){
        Product product = repository.getReferenceById(id);

        copyDtoToEntity(productDTO, product);

        Product productSaved = repository.save(product);

        return new ProductDTO(productSaved);
    }

    @Transactional
    private void copyDtoToEntity(@NotNull ProductDTO productDTO, @NotNull Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
