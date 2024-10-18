package org.example.spring_demo_stockmanagement.bll.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.CategoryService;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.CategoryRepository;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(UUID id){
        return categoryRepository.findById(id).orElseThrow();
    }
}
