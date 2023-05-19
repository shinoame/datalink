package com.datalink.service;

import com.datalink.entity.UserProfile;

public interface UserProfileService {
    void saveUserProfile(UserProfile userProfile);
    void updateUserProfile(UserProfile userProfile);
    UserProfile findByUsername(String username);

}
