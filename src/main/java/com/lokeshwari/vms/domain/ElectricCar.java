package com.lokeshwari.vms.domain;
public class ElectricCar extends ElectricVehicle {
    public ElectricCar(String ch, String vN, String cp, String md, String cl, int bat) {
        super(ch, vN, cp, md, cl, bat);
    }
    @Override public String getDetails() {
        return String.format("ELECTRIC CAR: %s %s | Battery: %d kWh | Chassis: %s", getCompany(), getModelNo(), getBatteryKwh(), getChassisNo());
    }
}