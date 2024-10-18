package org.example.spring_demo_stockmanagement.bll.services;

import org.example.spring_demo_stockmanagement.dl.entities.stock.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    Category findById(UUID id);
}
