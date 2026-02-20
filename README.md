# Vehicle Management System (VMS)

A professional-grade Java application built with a **3-Tier Architecture** and **Multi-level Hierarchical Inheritance**. This system manages a diverse fleet of vehicles (Electric & Internal Combustion) using a MySQL backend.


## 🏗️ Architecture & Design Patterns
This project follows the industry-standard 3-tier structure to ensure separation of concerns:

1.  **Presentation Layer (Main)**: A nested, menu-driven console interface for a seamless user experience.
2.  **Service Layer (FleetService)**: Handles business logic, data validation, and bridges the UI to the Data Layer.
3.  **Data Access Layer (DAO)**: Uses JDBC and the **Data Access Object (DAO)** pattern to map database rows to Java objects polymorphically.


## 🧬 Class Hierarchy (Multi-level Inheritance)
The domain model is structured to be scalable and logically organized:

- **Level 1 (Base)**: `Vehicle` (Common traits like Chassis No, Company, Color)
- **Level 2 (Power Source)**: 
    - `ElectricVehicle` (Adds Battery Capacity logic)
    - `ICVehicle` (Adds Engine CC and Fuel Type logic)
- **Level 3 (Leaf Classes)**: 
    - `ElectricCar`, `ElectricBike`
    - `ICCar`, `ICBike`, `ICTruck`, `ICJeep`


## 🛠️ Features
- **Full CRUD Operations**: Create, Read, and Delete vehicles from the fleet.
- **Polymorphic Search**: Track specific vehicles by Chassis Number; the system automatically identifies if the result is a Tesla (Electric) or a BharatBenz (Truck).
- **Relational Database**: Persistent storage using **MySQL**.
- **Input Validation**: Logic-driven inputs that adapt based on the vehicle type selected.


## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- MySQL Server 8.0+
- MySQL Connector J (JDBC Driver)

### Setup
1. Clone the repository:
   ```bash
   git clone [https://github.com/lokeshwari-02/VehicleManagementSystem.git](https://github.com/YourUsername/VehicleManagementSystem.git)
