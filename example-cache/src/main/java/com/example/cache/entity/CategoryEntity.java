package com.example.cache.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryEntity implements Serializable {
    
    private Long id;
    
    // 分类名
    private String name;
    
    // 父分类id
    private Long parentCategoryId;
    
    // 分类等级
    private Integer categoryLevel;
    
    // 子分类 （一共三级
    private List<CategoryEntity> children;
    
}
