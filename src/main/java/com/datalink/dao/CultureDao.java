package com.datalink.dao;

import com.datalink.entity.Culture;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CultureDao {
    List<Culture> findAll();
    Culture findById(Integer id);
    void insert(Culture culture);
    void update(Culture culture);
    void delete(Integer id);
    List<Culture>findByName(String name);
}