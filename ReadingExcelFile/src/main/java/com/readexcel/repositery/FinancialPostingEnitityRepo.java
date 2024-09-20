package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.FinancialPostingEnitity;
@Repository
public interface FinancialPostingEnitityRepo extends JpaRepository<FinancialPostingEnitity, Long>{

}
