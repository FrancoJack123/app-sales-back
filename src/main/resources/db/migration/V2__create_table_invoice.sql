CREATE TABLE invoice
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    invoice_number VARCHAR(255) NULL,
    billing_date   DATE NULL,
    total_amount   DECIMAL NULL,
    order_id       BIGINT NULL,
    CONSTRAINT pk_invoice PRIMARY KEY (id)
);

ALTER TABLE invoice
    ADD CONSTRAINT uc_invoice_order UNIQUE (order_id);
