ALTER TABLE order_detail
    ADD CONSTRAINT FK_ORDERDETAIL_ON_ORDER FOREIGN KEY (order_id) REFERENCES `order` (id);

ALTER TABLE order_detail
    ADD CONSTRAINT FK_ORDERDETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);
