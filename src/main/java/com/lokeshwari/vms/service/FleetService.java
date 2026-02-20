package com.lokeshwari.vms.service;

import com.lokeshwari.vms.dao.VehicleDAO;
import com.lokeshwari.vms.domain.*;
import com.lokeshwari.vms.exception.VehicleNotFoundException;
import java.util.List;

public class FleetService {
    private VehicleDAO vehicleDAO = new VehicleDAO();

    public void registerVehicle(Vehicle v) {
        System.out.println("Processing " + v.getCompany() + " " + v.getModelNo() + " ---");

        // Logical check: If it's Electric, ensure it has a battery capacity
        if (v instanceof ElectricVehicle && ((ElectricVehicle) v).getBatteryKwh() <= 0) {
            System.out.println("Warning: Battery capacity is 0! Check input.");
        }

        vehicleDAO.saveVehicle(v);
    }

    public Vehicle trackVehicle(String chassisNo) throws VehicleNotFoundException {
        Vehicle v = vehicleDAO.findByChassis(chassisNo);
        if (v == null) {
            throw new VehicleNotFoundException("Chassis Number " + chassisNo + " not found in system.");
        }
        return v;
    }
    public void removeVehicle(String chassisNo) {
        System.out.println("Verifying deletion for " + chassisNo + " ---");
        vehicleDAO.deleteVehicle(chassisNo);
    }

    public void displayFullFleet() {
        List<Vehicle> fleet = vehicleDAO.getAllVehicles();
        System.out.println("\n---CENTRAL FLEET INVENTORY ---");
        for (Vehicle v : fleet) {
            System.out.println(v.getDetails());
        }
    }
}