package com.alexandra.assignment.repository;

import com.alexandra.assignment.model.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
