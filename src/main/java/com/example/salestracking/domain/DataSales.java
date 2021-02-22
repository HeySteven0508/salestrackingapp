package com.example.salestracking.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Data_Sales")
public class DataSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesid;

    private int userId;
    private Date dateEarning;
    private String CustomName;
    private String sourceOfIncome;
    private double amountEarnings;

}
