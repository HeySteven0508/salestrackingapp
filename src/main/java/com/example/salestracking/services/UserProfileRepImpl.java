package com.example.salestracking.services;


import com.example.salestracking.domain.UserProfile;
import com.example.salestracking.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileRepImpl {

    UserProfileRepository userProfileRepository;

    public UserProfileRepImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getMyProfile(){
        return userProfileRepository.findAll();
    }

    public void updateProfile(UserProfile myUserProfile){
         userProfileRepository.save(myUserProfile);
    }
}
