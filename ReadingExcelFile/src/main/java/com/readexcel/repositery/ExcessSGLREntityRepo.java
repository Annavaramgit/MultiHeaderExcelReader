package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.ExcessSGLREntity;
@Repository
public interface ExcessSGLREntityRepo extends JpaRepository<ExcessSGLREntity, Long> {

}
