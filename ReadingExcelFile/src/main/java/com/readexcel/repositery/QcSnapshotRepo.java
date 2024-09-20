package com.readexcel.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readexcel.entity.QcSnapshot;
@Repository
public interface QcSnapshotRepo extends JpaRepository<QcSnapshot, Long>{

}
