package com.example.salestracking.services;

import com.example.salestracking.domain.DataSales;
import com.example.salestracking.repositories.DataSalesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DataSalesRepImpl
{
    DataSalesRepository dataSalesRepository;

    public DataSalesRepImpl(DataSalesRepository dataSalesRepository) {
        this.dataSalesRepository = dataSalesRepository;
    }

    public List<DataSales> listAllSales(){
        return dataSalesRepository.findAll();
    }

    public void addSales(DataSales myDataSales){
        dataSalesRepository.save(myDataSales);
    }

    public List<DataSales> getTotalSalesInCurrentMonth(){

        // Get the today's date
        LocalDate dateToday = LocalDate.now();
        YearMonth yearMonthObject = YearMonth.of(dateToday.getYear(),dateToday.getMonth());


        LocalDate startDate = LocalDate.of(dateToday.getYear(),dateToday.getMonth(),1);
        LocalDate endDate = LocalDate.of(dateToday.getYear(),dateToday.getMonth(),yearMonthObject.lengthOfMonth());

        //Convert the LocalDate Object to Date Object in order to accept the arguments in repository method
        Date convertedStartDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date convertedEndDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dataSalesRepository.findByDateEarningBetween(convertedStartDate,convertedEndDate);
    }

    public List<Double> getTotalSalesByMonth(){

        // Get the today
        LocalDate begginingDate = LocalDate.of(2020,10,1);

        YearMonth yearMonthObject;
        LocalDate salesMonth;

        LocalDate startDate;
        LocalDate endDate;

        List<DataSales> currentSale;

        List<Double> totalSalesPerMonth = new ArrayList<>();


        for(int m = 0; m < 12; m++){
            salesMonth = begginingDate.plusMonths(m);

            yearMonthObject = YearMonth.of(salesMonth.getYear(),salesMonth.getMonth());

            startDate = salesMonth;
            endDate = LocalDate.of(salesMonth.getYear(),salesMonth.getMonth(),yearMonthObject.lengthOfMonth());

            //Convert the LocalDate Object to Date Object in order to accept the arguments in repository method
            Date convertedStartDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date convertedEndDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            currentSale = dataSalesRepository.findByDateEarningBetween(convertedStartDate,convertedEndDate);
            Double Sales = currentSale.stream()
                    .filter(dataSales -> dataSales.getAmountEarnings() > 0)
                    .mapToDouble(dataSales -> dataSales.getAmountEarnings())
                    .sum();
            totalSalesPerMonth.add(Sales);

        }



        return totalSalesPerMonth;
    }


}
