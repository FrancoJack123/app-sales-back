CREATE TABLE user
(
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    first_name              VARCHAR(255)          NOT NULL,
    last_name               VARCHAR(255)          NOT NULL,
    password                VARCHAR(255)          NULL,
    email                   VARCHAR(255)          NULL,
    account_non_expired     BIT(1)                NULL,
    account_non_locked      BIT(1)                NULL,
    credentials_non_expired BIT(1)                NULL,
    enabled                 BIT(1)                NULL,
    code                    VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);
