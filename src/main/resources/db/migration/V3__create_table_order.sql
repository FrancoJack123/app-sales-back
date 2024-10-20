CREATE TABLE `order`
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    order_date   date                  NULL,
    total_amount DECIMAL               NULL,
    customer_id  BIGINT                NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);
