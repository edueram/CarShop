CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS car (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    make VARCHAR(255),
    model VARCHAR(255),
    year VARCHAR (255),
    base_price DECIMAL),
    sku_code VARCHAR(255) DEFAULT substr(md5(random()::text), 1, 20);

INSERT INTO car ( make, model,year, base_price)
VALUES ('BMW','5', '2020', 35000.00 ),
        ( 'Audi', 'A4', '2021', 32000.00),
        ( 'Mercedes-Benz', 'C-Class', '2022', 40000.00),
        ('Toyota', 'Corolla', '2019', 18000.00),
        ( 'Honda', 'Civic', '2020', 20000.00),
        ( 'Volkswagen', 'Golf', '2021', 22000.00),
        ( 'Ford', 'Focus', '2018', 17000.00),
        ( 'Tesla', 'Model 3', '2022', 45000.00),
        ( 'Hyundai', 'Elantra', '2019', 19000.00),
        ( 'Kia', 'Optima', '2020', 21000.00);