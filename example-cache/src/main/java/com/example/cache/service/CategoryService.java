package com.example.cache.service;

import com.example.cache.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    
    List<CategoryEntity> getCategoryTree();
    
}
