package com.alexandra.assignment.service;

import com.alexandra.assignment.model.Device;
import com.alexandra.assignment.model.Measurements;
import com.alexandra.assignment.model.User;
import com.alexandra.assignment.repository.IDeviceRepository;
import com.alexandra.assignment.repository.IMeasurementsRepository;
import com.alexandra.assignment.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceService {

    IDeviceRepository iDeviceRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IMeasurementsRepository iMeasurementsRepository;

    @Autowired
    public DeviceService(IDeviceRepository iDeviceRepository) {
        this.iDeviceRepository = iDeviceRepository;
    }

    public List<Device> getAllDevices() {
        return (List<Device>) iDeviceRepository.findAll();
    }

    public Device getDevice(Integer id) {
        Optional<Device> device = iDeviceRepository.findById(id);
        return device.orElse(null);
    }

    public String deleteDevice(Integer id) {
        try {
            Device device = this.getDevice(id);
            User u = device.getUser();
            if(u!= null) {
                u.deleteDevice(device);
            }
            iDeviceRepository.delete(this.getDevice(id));
            Set<Measurements> measurements = device.getMeasurements();
            for(Measurements m: measurements)
            {
                iMeasurementsRepository.delete(m);
            }
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public Device saveDevice(Device device){
            return iDeviceRepository.save(device);
    }

    public Device updateDevice(Integer id, Device device){
        Device initialDevice= this.getDevice(id);
        initialDevice.setAddress(device.getAddress());
        initialDevice.setDescription(device.getDescription());
        initialDevice.setMaximumHourlyEnergyConsumption(device.getMaximumHourlyEnergyConsumption());
        Set<Measurements> measurements = initialDevice.getMeasurements();
        for(Measurements m: measurements)
        {
            m.setDevice(device);
            iMeasurementsRepository.save(m);
        }
        return iDeviceRepository.save(initialDevice);
    }

    public Device mapDevice(Integer id, Integer user_id) {
        Device device= this.getDevice(id);
        Optional<User> user = iUserRepository.findById(user_id);
        if(user.isPresent())
        {
            User u = user.get();
            device.setUser(u);
            u.addDevice(device);
            iDeviceRepository.save(device);
            iUserRepository.save(u);
            return device;
        }
        return null;
    }

    public User getUser(Integer id) {
        Optional<Device> device = iDeviceRepository.findById(id);
        if(device.isPresent()){
            Device d = device.get();
            return d.getUser();
        }
        return null;
    }

    public Set<Measurements> getMeasurements(Integer id, String date) {
        Optional<Device> device = iDeviceRepository.findById(id);
        if(device.isPresent()){
            Device d = device.get();
            Set<Measurements> currentM = d.getMeasurements();
            Set<Measurements> finalM = new HashSet<>();
            for(Measurements stock : currentM){
                if(stock.getDate().equals(date))
                {
                    finalM.add(stock);
                }
            }
            return finalM;
        }
        return null;
    }
}

