package com.lokeshwari.vms;

import com.lokeshwari.vms.domain.*;
import com.lokeshwari.vms.service.FleetService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FleetService service = new FleetService();

        while (true) {
            System.out.println("\n===== VEHICLE MANAGEMENT =====");
            System.out.println("1. Register ELECTRIC Vehicle");
            System.out.println("2. Register IC (Petrol/Diesel) Vehicle");
            System.out.println("3. Track Vehicle by Chassis");
            System.out.println("4. Show All Vehicles");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Exit");
            System.out.print("Main Menu Choice: ");

            int mainChoice = sc.nextInt();
            sc.nextLine();

            if (mainChoice == 6) break;

            switch (mainChoice) {
                case 1:
                    System.out.println("\n--- Electric Category ---");
                    System.out.println("1. Electric Car | 2. Electric Bike");
                    System.out.print("Select Category: ");
                    int eChoice = sc.nextInt(); sc.nextLine();

                    VehicleData data = collectCommonData(sc);

                    if (eChoice == 1) {
                        System.out.print("Battery (kWh): "); int bat = sc.nextInt();
                        service.registerVehicle(new ElectricCar(data.ch, data.vN, data.cp, data.md, data.cl, bat));
                    } else if (eChoice == 2) {
                        System.out.print("Battery (kWh): "); int bat = sc.nextInt();
                        service.registerVehicle(new ElectricBike(data.ch, data.vN, data.cp, data.md, data.cl, bat));
                    }
                    break;

                case 2: // IC SUB-MENU
                    System.out.println("\n--- IC Category ---");
                    System.out.println("1. IC Car | 2. IC Bike | 3. IC Truck | 4. IC Jeep");
                    System.out.print("Select Category: ");
                    int icChoice = sc.nextInt(); sc.nextLine();

                    VehicleData icData = collectCommonData(sc);
                    System.out.print("Fuel Type: "); String fuel = sc.nextLine();
                    System.out.print("Engine CC: "); double cc = sc.nextDouble();

                    if (icChoice == 1) service.registerVehicle(new ICCar(icData.ch, icData.vN, icData.cp, icData.md, icData.cl, fuel, cc));
                    else if (icChoice == 2) service.registerVehicle(new ICBike(icData.ch, icData.vN, icData.cp, icData.md, icData.cl, fuel, cc));
                    else if (icChoice == 3) service.registerVehicle(new ICTruck(icData.ch, icData.vN, icData.cp, icData.md, icData.cl, fuel, cc));
                    else if (icChoice == 4) service.registerVehicle(new ICJeep(icData.ch, icData.vN, icData.cp, icData.md, icData.cl, fuel, cc));
                    break;

                case 3:
                    System.out.print("Enter Chassis No: ");
                    String chSearch = sc.nextLine();
                    try {
                        System.out.println(service.trackVehicle(chSearch).getDetails());
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 4:
                    service.displayFullFleet();
                    break;

                case 5:
                    System.out.println("\n--- Deletion Module ---");
                    System.out.print("Enter Chassis No to DELETE: ");
                    String chToDelete = sc.nextLine();
                    System.out.print("Are you sure? (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        service.removeVehicle(chToDelete);
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                    break;
            }
        }
    }

    private static VehicleData collectCommonData(Scanner sc) {
        System.out.print("Company: "); String cp = sc.nextLine();
        System.out.print("Model: "); String md = sc.nextLine();
        System.out.print("Chassis No: "); String ch = sc.nextLine();
        System.out.print("Vehicle No: "); String vN = sc.nextLine();
        System.out.print("Color: "); String cl = sc.nextLine();
        return new VehicleData(ch, vN, cp, md, cl);
    }
}
class VehicleData {
    String ch, vN, cp, md, cl;
    VehicleData(String ch, String vN, String cp, String md, String cl) {
        this.ch = ch; this.vN = vN; this.cp = cp; this.md = md; this.cl = cl;
    }
}