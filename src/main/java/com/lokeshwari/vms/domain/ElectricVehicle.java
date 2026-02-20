package com.lokeshwari.vms.domain;

public abstract class ElectricVehicle extends Vehicle {
    private int batteryCapacity;

    public ElectricVehicle(String ch, String vN, String cp, String md, String cl, int bat) {
        super(ch, vN, cp, md, cl, "Electric");
        this.batteryCapacity = bat;
    }

    // UPDATE: Change this or add this to match your VehicleDAO
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    // Keep this as an alias if needed, or remove it if you only use the one above
    public int getBatteryKwh() {
        return batteryCapacity;
    }
}