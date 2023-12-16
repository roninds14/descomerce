package com.roninds.dscommerce.services;

import com.roninds.dscommerce.dto.CategoryDTO;
import com.roninds.dscommerce.entities.Category;
import com.roninds.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = repository.findAll();

        return result.stream().map(CategoryDTO::new).toList();
    }
}
