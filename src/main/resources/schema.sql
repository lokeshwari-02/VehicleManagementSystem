-- This starts fresh with the exact columns your Java DAO is looking for
DROP TABLE IF EXISTS vehicles;

CREATE TABLE vehicles (
                          chassis_no VARCHAR(50) PRIMARY KEY,
                          vehicle_no VARCHAR(20) UNIQUE,
                          company VARCHAR(50),
                          model_no VARCHAR(50),
                          color VARCHAR(20),
                          vehicle_category VARCHAR(20), -- This was the missing column!
                          fuel_type VARCHAR(20),
                          battery_capacity INT,
                          engine_cc DOUBLE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);