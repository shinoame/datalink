package com.datalink.service;

import com.datalink.entity.Food;
import java.util.List;

public interface FoodService {
    List<Food> findAll();
    Food findById(Integer id);
    void insert(Food food);
    void update(Food food);
    void delete(Integer id);
    List<Food> findByName(String name);


}
