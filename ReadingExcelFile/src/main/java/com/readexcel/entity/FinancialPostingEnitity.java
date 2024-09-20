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

public class FinancialPostingEnitity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long financialid;

	private int financialPopulation;
	private int financialSample;
	private double financialSampleRate;
	private int financialError;
	private double financialQualityScore;

}
