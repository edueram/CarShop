CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS inventory
(
    id BIGSERIAL PRIMARY KEY,
    sku_code VARCHAR(255) NOT NULL,
    quantity INT DEFAULT NULL
);
INSERT INTO inventory (sku_code, quantity)
VALUES ('113b3b87803df7d98112',20 ),
       ('test1_skuCode',13 ),
       ('test2_skuCode', 200),
       ('4de4e7165d6bd54d34ad',20 ),
       ('0f7ee5f82237d5502514',50 ),
       ('ff97376eedd23ba200ef',80 ),
       ('7a10c763510824ff7866',90 ),
       ('6152051ec51965129b48', 50);