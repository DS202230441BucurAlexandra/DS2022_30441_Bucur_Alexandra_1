package com.alexandra.assignment.controller;

import com.alexandra.assignment.model.Device;
import com.alexandra.assignment.model.Measurements;
import com.alexandra.assignment.model.User;
import com.alexandra.assignment.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/devices")
public class DeviceController {

    public DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Device> getDevices() {
        return deviceService.getAllDevices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDevice")
    @ResponseBody
    public Device getDevice(@RequestParam(name = "id") Integer id) {
        return deviceService.getDevice(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteDevice")
    @ResponseBody
    public String deleteDevice(@RequestParam(name = "id") Integer id) {
        return deviceService.deleteDevice(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDevice")
    @ResponseBody
    public Device saveDevice(@RequestBody  Device device) {
        return deviceService.saveDevice(device);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateDevice")
    @ResponseBody
    public Device updateDevice(@RequestParam(name = "id") Integer id, @RequestBody Device device) {
        return deviceService.updateDevice(id, device);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mapDevice")
    @ResponseBody
    public Device mapDevice(@RequestParam(name = "id") Integer id, @RequestParam(name = "user_id") Integer user_id) {
        return deviceService.mapDevice(id, user_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") Integer id) {
        return deviceService.getUser(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getMeasurements")
    @ResponseBody
    public Set<Measurements> getMeasurements(@RequestParam(name = "id") Integer id , @RequestParam(name = "date") String date) {
        return deviceService.getMeasurements(id,date);
    }

}



