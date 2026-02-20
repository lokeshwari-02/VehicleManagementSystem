package com.lokeshwari.vms.domain;
public abstract class ICVehicle extends Vehicle {
    private double engineCc;
    public ICVehicle(String ch, String vN, String cp, String md, String cl, String fuel, double cc) {
        super(ch, vN, cp, md, cl, fuel); // Petrol, Diesel, etc.
        this.engineCc = cc;
    }
    public double getEngineCc() { return engineCc; }
}