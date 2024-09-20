//package com.readexcel.entity;
//import java.util.Date;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Entity
//
//public class ATMData {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Date trDate; // Transaction Date
//
//    // CRM Dispute and Claim Management
//    private int crmPopulation;
//    private int crmSample;
//    private double crmSampleRate;
//    private int crmError;
//    private double crmQualityScore;
//
//    // ATM Cash Forecasting
//    private int cashForecastPopulation;
//    private int cashForecastSample;
//    private double cashForecastSampleRate;
//    private int cashForecastError;
//    private double cashForecastQualityScore;
//
//    // Excess and Shortage GL Recon
//    private int glReconPopulation;
//    private int glReconSample;
//    private double glReconSampleRate;
//    private int glReconError;
//    private double glReconQualityScore;
//

//
//    // Financial Postings
//    private int financialPostingsPopulation;
//    private int financialPostingsSample;
//    private double financialPostingsSampleRate;
//    private int financialPostingsError;
//    private double financialPostingsQualityScore;
//
//    // ATM/CCDM Installation/Removal/Relocation
//    private int atmInstallationPopulation;
//    private int atmInstallationSample;
//    private double atmInstallationSampleRate;
//    private int atmInstallationError;
//    private double atmInstallationQualityScore;
//
//
//}
