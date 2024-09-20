package com.readexcel.entity;

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

public class ExcessSGLREntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long excesssglrid;
	
	private int excesssglrPopulation;
	private int excesssglrSample;
	private double excesssglrSampleRate;
	private int excesssglrError;
	private double excesssglrQualityScore;
	
	

}
