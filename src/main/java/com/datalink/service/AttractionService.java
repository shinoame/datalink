package com.datalink.service;

import com.datalink.entity.Attraction;

import java.util.List;

public interface AttractionService {
    List<Attraction> findAll();
    Attraction findById(Integer id);
    void insert(Attraction attraction);
    void update(Attraction attraction);
    void delete(Integer id);
    List<Attraction>findByName(String name);
}
