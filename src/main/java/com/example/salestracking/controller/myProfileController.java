package com.example.salestracking.controller;

import com.example.salestracking.domain.UserProfile;
import com.example.salestracking.services.UserProfileRepImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/myProfile")
public class myProfileController {

    private final UserProfileRepImpl userProfileRep;

    @Autowired
    public myProfileController(UserProfileRepImpl userProfileRep) {
        this.userProfileRep = userProfileRep;
    }

    @GetMapping
    public String showMyProfile(Model model){
        UserProfile myProfile = userProfileRep.getMyProfile().get(0);
        model.addAttribute("userProfile",myProfile);
        return "myProfilePage";
    }

    @PostMapping
    public String updateMyProfile(@ModelAttribute UserProfile updatedUserProfile){
        UserProfile originalUserProfile = userProfileRep.getMyProfile().get(0);
        originalUserProfile.setFirstName(updatedUserProfile.getFirstName());
        originalUserProfile.setLastName(updatedUserProfile.getLastName());
        originalUserProfile.setLocation(updatedUserProfile.getLocation());
        userProfileRep.updateProfile(originalUserProfile);
        return "redirect:/index";
    }
}
