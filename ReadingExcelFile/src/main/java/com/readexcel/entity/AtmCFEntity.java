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

public class AtmCFEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long atmcfid;

	private int atmcfePopulation;
	private int atmcfeSample;
	private double atmcfeSampleRate;
	private int atmcfeError;
	private double atmcfeQualityScore;

}
