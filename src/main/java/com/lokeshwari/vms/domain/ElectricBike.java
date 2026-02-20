package com.lokeshwari.vms.domain;

public class ElectricBike extends ElectricVehicle {
    public ElectricBike(String ch, String vN, String cp, String md, String cl, int bat) {
        super(ch, vN, cp, md, cl, bat);
    }

    @Override
    public String getDetails() {
        return String.format("ELECTRIC BIKE: %s %s | Battery: %d kWh | Chassis: %s",
                getCompany(), getModelNo(), getBatteryKwh(), getChassisNo());
    }
}