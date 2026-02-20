package com.lokeshwari.vms.domain;
public class ICCar extends ICVehicle {
    public ICCar(String ch, String vN, String cp, String md, String cl, String fuel, double cc) {
        super(ch, vN, cp, md, cl, fuel, cc);
    }
    @Override public String getDetails() {
        return String.format("IC CAR: %s %s | Fuel: %s (%.0f CC) | Chassis: %s",
                getCompany(), getModelNo(), getFuelType(), getEngineCc(), getChassisNo());
    }
}