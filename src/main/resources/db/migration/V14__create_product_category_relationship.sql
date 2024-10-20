CREATE TABLE product_category
(
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_product_category PRIMARY KEY (category_id, product_id)
);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_product FOREIGN KEY (product_id) REFERENCES product (id);