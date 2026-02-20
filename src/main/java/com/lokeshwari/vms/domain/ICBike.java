package com.lokeshwari.vms.domain;
public class ICBike extends ICVehicle {
    public ICBike(String ch, String vN, String cp, String md, String cl, String fuel, double cc) {
        super(ch, vN, cp, md, cl, fuel, cc);
    }
    @Override public String getDetails() {
        return String.format("IC BIKE: %s %s | Engine: %.1f CC | Fuel: %s | Chassis: %s", getCompany(), getModelNo(), getEngineCc(), getFuelType(), getChassisNo());
    }
}