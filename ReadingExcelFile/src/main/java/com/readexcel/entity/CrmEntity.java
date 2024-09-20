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

public class CrmEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long crmid;

	private int crmPopulation;
	private int crmSample;
	private double crmSampleRate;
	private int crmError;
	private double crmQualityScore;

}
