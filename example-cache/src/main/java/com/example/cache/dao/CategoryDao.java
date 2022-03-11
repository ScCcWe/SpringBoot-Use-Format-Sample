package com.example.cache.dao;

import com.example.cache.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    
    List<CategoryEntity> getLevelOneCategory();

    List<CategoryEntity> getCategoryList();
    
}
