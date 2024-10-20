CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    img_url       VARCHAR(255)          NULL,
    price         DECIMAL               NULL,
    stock         DECIMAL               NULL,
    supplier_id   BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);
