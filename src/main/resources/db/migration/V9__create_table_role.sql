CREATE TABLE `role`
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    role_name ENUM('ADMIN', 'USER') NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);