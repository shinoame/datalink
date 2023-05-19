package com.datalink.dao;

import com.datalink.entity.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodDao {
    List<Food> findAll();
    Food findById(Integer id);
    void insert(Food food);
    void update(Food food);
    void delete(Integer id);
    List<Food> findByName(String name);

}
