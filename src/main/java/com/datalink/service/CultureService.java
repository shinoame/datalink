package com.datalink.service;

import com.datalink.entity.Culture;
import java.util.List;

public interface CultureService {
    List<Culture> findAll();
    Culture findById(Integer id);
    void save(Culture culture);
    void update(Culture culture);
    void delete(Integer id);
    void  insert(Culture culture);
    List<Culture>findByName(String name);
}
