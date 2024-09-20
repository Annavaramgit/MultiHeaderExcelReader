package com.readexcel.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QcSnapshot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date trDate;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="crmid")
	private CrmEntity crmEntity;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="atmcfid")
	private AtmCFEntity atmCFEntity;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="excesssglrid")
	private ExcessSGLREntity excessSGLREntity;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="financialid")
    private FinancialPostingEnitity financialPostingEnitity;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="atmccdmid")
    private AtmCCDMEntity atmCCDMEntity;
	
}
