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

@Controller
@RequestMapping("/myJob")
public class MyJobController {

    private final UserProfileRepImpl userProfileRep;

    @Autowired
    public MyJobController(UserProfileRepImpl userProfileRep) {
        this.userProfileRep = userProfileRep;
    }

    @GetMapping
    public String showMyJob(Model model){
        UserProfile myProfile = userProfileRep.getMyProfile().get(0);
        model.addAttribute("myUserProfile",myProfile);
        return "myJobPage";
    }

    @PostMapping
    public String updateMyJob(@ModelAttribute UserProfile updatedUserProfile){
        UserProfile myUserProfile = userProfileRep.getMyProfile().get(0);
        myUserProfile.setTypeOfWork(updatedUserProfile.getTypeOfWork());
        myUserProfile.setSalary(updatedUserProfile.getSalary());

        userProfileRep.updateProfile(myUserProfile);

        return "redirect:/";
    }
}
