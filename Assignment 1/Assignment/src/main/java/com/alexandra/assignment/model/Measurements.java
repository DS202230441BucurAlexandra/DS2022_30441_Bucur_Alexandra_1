package com.alexandra.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Measurements {

    @Id
    @Column(name ="measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer timestamp;

    private Date date;

    private Integer energyConsumption;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Device device;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Measurements() {
    }

    public Measurements(Integer id, Integer timestamp, Date date, Integer energyConsumption, Device device) {
        this.id = id;
        this.timestamp = timestamp;
        this.date = date;
        this.energyConsumption = energyConsumption;
        this.device = device;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
