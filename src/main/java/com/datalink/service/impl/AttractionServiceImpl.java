package com.datalink.service.impl;

import com.datalink.dao.AttractionDao;
import com.datalink.entity.Attraction;
import com.datalink.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionDao attractionDao;

    @Override
    public List<Attraction> findAll() {
        return attractionDao.findAll();
    }

    @Override
    public Attraction findById(Integer id) {
        return attractionDao.findById(id);
    }

    @Override
    public void insert(Attraction attraction) {
        attractionDao.insert(attraction);
    }

    @Override
    public void update(Attraction attraction) {
        attractionDao.update(attraction);
    }

    @Override
    public void delete(Integer id) {
        attractionDao.delete(id);
    }

    @Override
    public List<Attraction> findByName(String name) {
        return attractionDao.findByName(name);
    }
}