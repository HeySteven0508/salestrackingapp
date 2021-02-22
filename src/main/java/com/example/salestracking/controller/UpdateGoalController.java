package com.example.salestracking.controller;

import com.example.salestracking.domain.UserProfile;
import com.example.salestracking.services.UserProfileRepImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addGoal")
public class UpdateGoalController {

    private final UserProfileRepImpl userProfileRep;

    @Autowired
    public UpdateGoalController(UserProfileRepImpl userProfileRep) {
        this.userProfileRep = userProfileRep;
    }

    @PostMapping
    public String updateGoal(@ModelAttribute UserProfile userProfile, Model model ){
        UserProfile myUserProfile = userProfileRep.getMyProfile().get(0);
        myUserProfile.setMonthlyGoal(userProfile.getMonthlyGoal());
        userProfileRep.updateProfile(myUserProfile);
        return "redirect:/";

    }
}
