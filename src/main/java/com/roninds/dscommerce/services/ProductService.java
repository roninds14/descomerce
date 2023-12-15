package com.roninds.dscommerce.services;

import com.roninds.dscommerce.dto.ProductDTO;
import com.roninds.dscommerce.dto.ProductMinDTO;
import com.roninds.dscommerce.entities.Product;
import com.roninds.dscommerce.repositories.ProductRepository;
import com.roninds.dscommerce.services.exceptions.DatabaseException;
import com.roninds.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);

        return result.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findMinAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);

        return result.map(ProductMinDTO::new);
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
        try{
            Product product = repository.getReferenceById(id);

            copyDtoToEntity(productDTO, product);

            Product productSaved = repository.save(product);

            return new ProductDTO(productSaved);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional
    private void copyDtoToEntity(@NotNull ProductDTO productDTO, @NotNull Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
