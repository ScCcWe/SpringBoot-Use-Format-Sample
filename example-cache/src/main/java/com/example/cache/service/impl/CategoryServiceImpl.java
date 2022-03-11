package com.example.cache.service.impl;

import com.example.cache.dao.CategoryDao;
import com.example.cache.entity.CategoryEntity;
import com.example.cache.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    
    @Autowired
    CategoryDao categoryDao;
    
    /*
    * 
    * @Cacheable: 
    *   Triggers cache population.
    *   触发缓存填充
    * 
    * sync = true: 
    *   加锁解决击穿（本地锁，非分布式锁
    * 
    * */
    @Cacheable(value = "category", key="#root.methodName", sync = true)    
    @Override
    public List<CategoryEntity> getCategoryTree() {
        System.out.println("查询数据库");

        // STEP-1 获取所有分类
        List<CategoryEntity> categoryEntityList = categoryDao.getCategoryList();
        
        // STEP-2 将分类的children拼上去
        List<CategoryEntity> collect = categoryEntityList.stream().filter(categoryEntity -> {
            // 过滤出来一级分类
            return categoryEntity.getCategoryLevel() == 1;
        }).map(levelOneCategory -> {
            // 给一级分类 插入子分类
            levelOneCategory.setChildren(findChildren(levelOneCategory, categoryEntityList));
            return levelOneCategory;
        }).collect(Collectors.toList());

        return collect;
    }
    
    // 递归查找所有分类的子分类
    private List<CategoryEntity> findChildren(CategoryEntity curCategory, List<CategoryEntity> categoryEntityList) {

        List<CategoryEntity> collect = categoryEntityList.stream()
                // 过滤出 `所有分类中父分类id == 当前分类id` 的分类 
                .filter(categoryEntity -> categoryEntity.getParentCategoryId().equals(curCategory.getId()))
                .map(curLevelCategory -> {
                    curLevelCategory.setChildren(findChildren(curLevelCategory, categoryEntityList));
                    return curLevelCategory;
                }).collect(Collectors.toList());

        return collect;
    }


}
