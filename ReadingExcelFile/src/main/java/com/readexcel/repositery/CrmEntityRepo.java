package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.CrmEntity;
@Repository
public interface CrmEntityRepo extends JpaRepository<CrmEntity, Long>{

}
