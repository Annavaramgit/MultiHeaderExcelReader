package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.AtmCFEntity;

@Repository
public interface AtmCFEntityRepo extends JpaRepository<AtmCFEntity, Long>{

}
