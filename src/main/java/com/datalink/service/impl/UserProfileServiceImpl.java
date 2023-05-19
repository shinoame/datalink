package com.datalink.service.impl;

import com.datalink.dao.UserProfileDao;
import com.datalink.entity.UserProfile;
import com.datalink.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;


    @Override
    public void saveUserProfile(UserProfile userProfile) {
        userProfileDao.saveUserProfile(userProfile);
    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        userProfileDao.updateUserProfile(userProfile);
    }

    @Override
    public UserProfile findByUsername(String username) {
        return userProfileDao.findByUsername( username);
    }
}
