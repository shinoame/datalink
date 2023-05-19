package com.datalink.dao;

import com.datalink.entity.Attraction;
import com.datalink.entity.Culture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionDao {
    List<Attraction> findAll();
    Attraction findById(Integer id);
    void insert(Attraction attraction);
    void update(Attraction attraction);
    void delete(Integer id);
    List<Attraction>findByName(String name);
}