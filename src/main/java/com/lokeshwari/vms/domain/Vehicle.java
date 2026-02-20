package com.lokeshwari.vms.domain;

public abstract class Vehicle {
    private String chassisNo, vehicleNo, company, modelNo, color, fuelType;

    public Vehicle(String chassisNo, String vNo, String comp, String mod, String col, String fuel) {
        this.chassisNo = chassisNo; this.vehicleNo = vNo; this.company = comp;
        this.modelNo = mod; this.color = col; this.fuelType = fuel;
    }

    // Getters for DAO
    public String getChassisNo() { return chassisNo; }
    public String getVehicleNo() { return vehicleNo; }
    public String getCompany() { return company; }
    public String getModelNo() { return modelNo; }
    public String getColor() { return color; }
    public String getFuelType() { return fuelType; }

    public abstract String getDetails();
}