package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.AtmCCDMEntity;
@Repository
public interface AtmCCDMEntityRepo extends JpaRepository<AtmCCDMEntity, Long> {

}
