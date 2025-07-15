-- Activate UUID-Extension
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create table car_option
CREATE TABLE IF NOT EXISTS car_option (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    option_type VARCHAR(255),
    option_value VARCHAR(255),
    additional_price DECIMAL
    );

-- Color
INSERT INTO car_option (id, option_type, option_value, additional_price) VALUES
(gen_random_uuid(), 'Color', 'Red', 500.00),
(gen_random_uuid(), 'Color', 'Blue', 400.00),
(gen_random_uuid(), 'Color', 'Black', 600.00),
(gen_random_uuid(), 'Color', 'White', 300.00);

-- Engine
INSERT INTO car_option (id, option_type, option_value, additional_price) VALUES
(gen_random_uuid(), 'Engine', 'V4', 0.00),
(gen_random_uuid(), 'Engine', 'V6', 2000.00),
(gen_random_uuid(), 'Engine', 'Electric', 5000.00);

-- Rim
INSERT INTO car_option (id, option_type, option_value, additional_price) VALUES
 (gen_random_uuid(), 'Rim', 'Standard', 0.00),
(gen_random_uuid(), 'Rim', 'Sport', 800.00),
(gen_random_uuid(), 'Rim', 'Premium', 1500.00);

-- Special Equipment
INSERT INTO car_option (id, option_type, option_value, additional_price)
VALUES
(gen_random_uuid(), 'Special Equipment', 'Leather Seats', 1200.00),
 (gen_random_uuid(), 'Special Equipment', 'Panoramic Sunroof', 1800.00),
(gen_random_uuid(), 'Special Equipment', 'Navigation System', 1000.00),
 (gen_random_uuid(), 'Special Equipment', 'Premium Sound System', 1500.00);
