package com.datalink.service.impl;

import com.datalink.entity.Food;
import com.datalink.dao.FoodDao;
import com.datalink.service.FoodService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDao foodDao;

    @Override
    public List<Food> findAll() {
        return foodDao.findAll();
    }

    @Override
    public Food findById(Integer id) {
        return foodDao.findById(id);
    }

    @Override
    public void insert(Food food) {
        foodDao.insert(food);
    }

    @Override
    public void update(Food food) {
        foodDao.update(food);
    }

    @Override
    public void delete(Integer id) {
        foodDao.delete(id);
    }
    @Override
    public List<Food> findByName(String name) {
        return foodDao.findByName(name);
    }

}
