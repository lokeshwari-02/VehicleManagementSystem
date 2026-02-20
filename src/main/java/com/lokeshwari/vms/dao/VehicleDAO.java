package com.lokeshwari.vms.dao;

import com.lokeshwari.vms.domain.*;
import com.lokeshwari.vms.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class VehicleDAO {

    // 1. READ ALL: Converts all DB rows into a Polymorphic List
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToVehicle(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Database Error: " + e.getMessage());
        }
        return list;
    }

    // 2. CREATE: Saves any Vehicle type (Electric or IC) to the same table
    public void saveVehicle(Vehicle v) {
        String sql = "INSERT INTO vehicles (chassis_no, vehicle_no, company, model_no, color, vehicle_category, fuel_type, battery_capacity, engine_cc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, v.getChassisNo());
            pstmt.setString(2, v.getVehicleNo());
            pstmt.setString(3, v.getCompany());
            pstmt.setString(4, v.getModelNo());
            pstmt.setString(5, v.getColor());
            pstmt.setString(6, v.getClass().getSimpleName().toUpperCase()); // Discriminator
            pstmt.setString(7, v.getFuelType());

            // Handle Multi-level specifics
            if (v instanceof ElectricVehicle) {
                pstmt.setInt(8, ((ElectricVehicle) v).getBatteryCapacity());
                pstmt.setNull(9, java.sql.Types.DOUBLE);
            } else if (v instanceof ICVehicle) {
                pstmt.setNull(8, java.sql.Types.INTEGER);
                pstmt.setDouble(9, ((ICVehicle) v).getEngineCc());
            }
            pstmt.executeUpdate();
            System.out.println("✅ " + v.getClass().getSimpleName() + " registered successfully.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 3. READ ONE: Finds specific vehicle by Chassis Number
    public Vehicle findByChassis(String chassisNo) {
        String sql = "SELECT * FROM vehicles WHERE chassis_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, chassisNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapResultSetToVehicle(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // HELPER: Reconstructs the correct "Leaf" object based on category
    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        String category = rs.getString("vehicle_category");
        String ch = rs.getString("chassis_no");
        String vn = rs.getString("vehicle_no");
        String cp = rs.getString("company");
        String md = rs.getString("model_no");
        String cl = rs.getString("color");
        String fl = rs.getString("fuel_type");

        return switch (category) {
            case "ELECTRICCAR" -> new ElectricCar(ch, vn, cp, md, cl, rs.getInt("battery_capacity"));
            case "ELECTRICBIKE" -> new ElectricBike(ch, vn, cp, md, cl, rs.getInt("battery_capacity"));
            case "ICCAR" -> new ICCar(ch, vn, cp, md, cl, fl, rs.getDouble("engine_cc"));
            case "ICBIKE" -> new ICBike(ch, vn, cp, md, cl, fl, rs.getDouble("engine_cc"));
            case "ICTRUCK" -> new ICTruck(ch, vn, cp, md, cl, fl, rs.getDouble("engine_cc"));
            case "ICJEEP" -> new ICJeep(ch, vn, cp, md, cl, fl, rs.getDouble("engine_cc"));
            default -> null;
        };
    }
    public void deleteVehicle(String chassisNo) {
        String sql = "DELETE FROM vehicles WHERE chassis_no = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, chassisNo);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Database: Vehicle with Chassis [" + chassisNo + "] removed successfully.");
            } else {
                System.out.println("Database: No vehicle found with Chassis [" + chassisNo + "]. Nothing deleted.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error during deletion: " + e.getMessage());
        }
    }

}