package com.example.cache.controller;

import com.example.cache.dao.CategoryDao;
import com.example.cache.entity.CategoryEntity;
import com.example.cache.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {


    @Autowired
    CategoryService categoryService;
    
    
    @Autowired
    CategoryDao categoryDao;
    
    
    @GetMapping("/level-one")
    public List<CategoryEntity> getLevelOneCategory(){
        
        List<CategoryEntity> entities = categoryDao.getLevelOneCategory();
        
        return entities;
        
    }
    
    
    /*
    * 
    * 获取三级分类
    * 
    * */
    @GetMapping("/tree")
    public List<CategoryEntity> getCategoryTree() {
        
        List<CategoryEntity> entities = categoryService.getCategoryTree();

        return entities;

    }
    

}
