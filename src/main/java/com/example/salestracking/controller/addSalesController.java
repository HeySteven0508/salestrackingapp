package com.example.salestracking.controller;


import com.example.salestracking.domain.DataSales;
import com.example.salestracking.services.DataSalesRepImpl;
import com.example.salestracking.services.UserProfileRepImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping("/addSales")
public class addSalesController {

    DataSalesRepImpl dataSalesRep;
    UserProfileRepImpl userProfileRep;


    public addSalesController(DataSalesRepImpl dataSalesRep, UserProfileRepImpl userProfileRep) {
        this.dataSalesRep = dataSalesRep;
        this.userProfileRep = userProfileRep;
    }

    @GetMapping
    public String showAddSales(Model model){
        DataSales myDataSales = new DataSales();

        model.addAttribute("myDataSales", myDataSales);
        model.addAttribute("myUserProfile",userProfileRep.getMyProfile().get(0));

        return "addSalesPage";
    }

    @PostMapping
    public String addSales(@ModelAttribute DataSales myDataSales){

        // java.sql.Date
        Calendar calendar = Calendar.getInstance();
        Date ourJavaDateObject = new Date(calendar.getTime().getTime());

        // Adding Current Date
        myDataSales.setDateEarning(ourJavaDateObject);

        // Adding User ID
        myDataSales.setUserId(1);
        dataSalesRep.addSales(myDataSales);
        return "redirect:/index";
    }
}
