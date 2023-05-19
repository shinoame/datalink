package com.datalink.service.impl;

import com.datalink.dao.CultureDao;
import com.datalink.entity.Culture;
import com.datalink.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureServiceImpl implements CultureService {

    @Autowired
    private CultureDao cultureDao;

    @Override
    public List<Culture> findAll() {
        return cultureDao.findAll();
    }

    @Override
    public Culture findById(Integer id) {
        return cultureDao.findById(id);
    }

    @Override
    public void save(Culture culture) {
        cultureDao.insert(culture);
    }

    @Override
    public void update(Culture culture) {
        cultureDao.update(culture);
    }

    @Override
    public void delete(Integer id) {
        cultureDao.delete(id);
    }

    @Override
    public void insert(Culture culture) {
        cultureDao.insert(culture);
    }

    @Override
    public List<Culture> findByName(String name) {
      return cultureDao.findByName( name);
    }
}
