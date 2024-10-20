CREATE TABLE supplier
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)          NULL,
    ruc          VARCHAR(255)          NULL,
    contact_info VARCHAR(255)          NULL,
    CONSTRAINT pk_supplier PRIMARY KEY (id)
);

ALTER TABLE supplier
    ADD CONSTRAINT uc_supplier_ruc UNIQUE (ruc);
