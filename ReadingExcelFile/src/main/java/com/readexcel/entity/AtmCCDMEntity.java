package com.readexcel.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtmCCDMEntity {
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long atmccdmid;
	   
	    private int atmccdmPopulation;
	    private int atmccdmSample;
	    private double atmccdmSampleRate;
	    private int atmccdmError;
	    private double atmccdmQualityScore;
	   
	  

}
