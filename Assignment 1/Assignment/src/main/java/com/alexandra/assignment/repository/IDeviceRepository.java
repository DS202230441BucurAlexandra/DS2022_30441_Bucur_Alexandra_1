package com.alexandra.assignment.repository;
import com.alexandra.assignment.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<Device, Integer> {

}
