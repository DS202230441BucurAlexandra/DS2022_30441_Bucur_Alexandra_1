package com.alexandra.assignment.service;

import com.alexandra.assignment.model.Device;
import com.alexandra.assignment.model.Measurements;
import com.alexandra.assignment.repository.IDeviceRepository;
import com.alexandra.assignment.repository.IMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementsService {

    IMeasurementsRepository iMeasurementsRepository;
    @Autowired
    IDeviceRepository iDeviceRepository;

    @Autowired
    public MeasurementsService(IMeasurementsRepository iMeasurementsRepository) {
        this.iMeasurementsRepository = iMeasurementsRepository;
    }

    public List<Measurements> getAllMeasurements() {
        return (List<Measurements>) iMeasurementsRepository.findAll();
    }

    public Measurements getMeasurement(Integer id) {
        Optional<Measurements> measurement = iMeasurementsRepository.findById(id);
        return measurement.orElse(null);
    }

    public Measurements saveM(Measurements m, Integer id) {
        Optional<Device> d = iDeviceRepository.findById(id);
        if(d.isPresent()) {
            Device device = d.get();
            m.setDevice(device);
            device.addMeasurement(m);
            return iMeasurementsRepository.save(m);
        }
        return null;
    }
}