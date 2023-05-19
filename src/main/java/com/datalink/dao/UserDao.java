package com.datalink.dao;

import com.datalink.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findByUsername(String username);
    void saveUser(User user);
}
