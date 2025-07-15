CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS inventory
(
    id bigint (20) NOT NULL AUTO_INCREMET,
    sku_id VARCHAR(255) NOT NULL,
    quantity int (11) DEFAULT NULL,

)
INSERT INTO inventory ( make, model,year, base_price)
VALUES ('test_Sku',20 ),
       ('test2_Sku', 50);