package com.datalink.dao;

import com.datalink.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileDao {
    UserProfile findByUsername(String name);
    void saveUserProfile(UserProfile userProfile);
    void updateUserProfile(UserProfile userProfile);
}
