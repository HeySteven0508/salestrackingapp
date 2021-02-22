package com.example.salestracking.controller;

import com.example.salestracking.domain.DataSales;
import com.example.salestracking.domain.UserProfile;
import com.example.salestracking.services.DataSalesRepImpl;
import com.example.salestracking.services.UserProfileRepImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/index","/"})
public class indexController {

    DataSalesRepImpl dataSalesRep;
    UserProfileRepImpl userProfileRep;

    public indexController(DataSalesRepImpl dataSalesRep, UserProfileRepImpl userProfileRep) {
        this.dataSalesRep = dataSalesRep;
        this.userProfileRep = userProfileRep;
    }

    @GetMapping
    public String showIndex(Model model){
        double TotalEarnings = indexController.getTotalSales(dataSalesRep.listAllSales());
        int totalCountOfJobs = dataSalesRep.listAllSales().size();
        double TotalMonthlyEarnings = getTotalSales(dataSalesRep.getTotalSalesInCurrentMonth());
        List<Double> totalSales = dataSalesRep.getTotalSalesByMonth();
        UserProfile myProfile = userProfileRep.getMyProfile().get(0);
        int percentageGoal = getGoalPercentage(myProfile.getMonthlyGoal(),TotalMonthlyEarnings);

        model.addAttribute("TotalJobCounts",totalCountOfJobs);
        model.addAttribute("TotalEarnings",TotalEarnings);
        model.addAttribute("TotalMonthlyEarnings",TotalMonthlyEarnings);
        model.addAttribute("MonthlySales",totalSales);
        model.addAttribute("MyProfile",myProfile);
        model.addAttribute("PercentageGoal",percentageGoal);
        return "index";
    }


    private static double getTotalSales(List<DataSales> myListOfSales) {

        double totalEarnings = 0.0;

        for(int i = 0; i < myListOfSales.size(); i++)
        {
            totalEarnings += myListOfSales.get(i).getAmountEarnings();
        }

        return totalEarnings;
    }


    private int getGoalPercentage(double earningGoal, double earningActual){
        return (int) ((earningActual/earningGoal) * 100);
    }



}

