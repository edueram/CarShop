-- Activate UUID-Extension
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create table car_option
CREATE TABLE IF NOT EXISTS car_option (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    option_type VARCHAR(255),
    option_value VARCHAR(255),
    additional_price DECIMAL,
    sku_code VARCHAR(255) DEFAULT substr(md5(random()::text), 1, 20);
    );

-- Color
INSERT INTO car_option ( option_type, option_value, additional_price) VALUES
( 'Color', 'Red', 500.00),
( 'Color', 'Blue', 400.00),
( 'Color', 'Black', 600.00),
( 'Color', 'White', 300.00);

-- Engine
INSERT INTO car_option ( option_type, option_value, additional_price) VALUES
( 'Engine', 'V4', 0.00),
( 'Engine', 'V6', 2000.00),
( 'Engine', 'Electric', 5000.00);

-- Rim
INSERT INTO car_option ( option_type, option_value, additional_price) VALUES
 ( 'Rim', 'Standard', 0.00),
( 'Rim', 'Sport', 800.00),
( 'Rim', 'Premium', 1500.00);

-- Special Equipment
INSERT INTO car_option ( option_type, option_value, additional_price)
VALUES
( 'Special Equipment', 'Leather Seats', 1200.00),
 ( 'Special Equipment', 'Panoramic Sunroof', 1800.00),
( 'Special Equipment', 'Navigation System', 1000.00),
 ( 'Special Equipment', 'Premium Sound System', 1500.00);
