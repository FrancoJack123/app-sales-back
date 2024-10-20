CREATE TABLE order_detail
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    quantity    DECIMAL               NULL,
    unit_price  DECIMAL               NULL,
    total_price DECIMAL               NULL,
    order_id    BIGINT                NULL,
    product_id  BIGINT                NULL,
    CONSTRAINT pk_orderdetail PRIMARY KEY (id)
);
