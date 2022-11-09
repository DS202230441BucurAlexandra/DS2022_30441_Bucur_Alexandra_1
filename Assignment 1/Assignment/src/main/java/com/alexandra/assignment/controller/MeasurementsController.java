package com.alexandra.assignment.controller;

import com.alexandra.assignment.model.Measurements;
import com.alexandra.assignment.service.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/measurements")
public class MeasurementsController {
    public MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Measurements> getMeasurements() {
        return measurementsService.getAllMeasurements();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getMeasurement")
    @ResponseBody
    public Measurements getMeasurement(@RequestParam(name = "id") Integer id) {
        return measurementsService.getMeasurement(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/saveM")
    @ResponseBody
    public Measurements saveDevice(@RequestParam(name = "device_id") Integer id, @RequestBody  Measurements m) {
        return measurementsService.saveM(m,id);
    }

}
