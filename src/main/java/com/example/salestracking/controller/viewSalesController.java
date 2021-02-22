package com.example.salestracking.controller;


import com.example.salestracking.domain.DataSales;
import com.example.salestracking.services.DataSalesRepImpl;
import com.example.salestracking.services.UserProfileRepImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/viewSales")
public class viewSalesController {

    DataSalesRepImpl dataSalesRep;
    UserProfileRepImpl userProfileRep;

    public viewSalesController(DataSalesRepImpl dataSalesRep, UserProfileRepImpl userProfileRep) {
        this.dataSalesRep = dataSalesRep;
        this.userProfileRep = userProfileRep;
    }

    @GetMapping
    public String showViewSales(Model model){

        List<DataSales> listAllSales = dataSalesRep.listAllSales();

        model.addAttribute("listSales",listAllSales);
        model.addAttribute("myUserProfile",userProfileRep.getMyProfile().get(0));
        return "viewSalesPage";
    }
}
