CREATE TABLE customer
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255)          NULL,
    email   VARCHAR(255)          NULL,
    phone   VARCHAR(255)          NULL,
    address VARCHAR(255)          NULL,
    city    VARCHAR(255)          NULL,
    state   VARCHAR(255)          NULL,
    zip     VARCHAR(255)          NULL,
    country VARCHAR(255)          NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

ALTER TABLE customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

ALTER TABLE customer
    ADD CONSTRAINT uc_customer_phone UNIQUE (phone);
