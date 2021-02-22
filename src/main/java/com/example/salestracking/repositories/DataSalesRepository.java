package com.example.salestracking.repositories;

import com.example.salestracking.domain.DataSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DataSalesRepository extends JpaRepository<DataSales,Integer> {
    List<DataSales> findByDateEarningBetween(Date startDate, Date endDate);
}
