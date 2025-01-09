CREATE TABLE IF NOT EXISTS T_USER
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    first_name        VARCHAR(50) NOT NULL,
    last_name         VARCHAR(50) NOT NULL,
    email             VARCHAR(50) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    role              VARCHAR(50) NOT NULL,
    active            bit(1) NOT NULL DEFAULT b'1',
    company_id        BIGINT(20),
    deactivated_timestamp TIMESTAMP NULL,
    created_date TIMESTAMP NOT NULL default current_timestamp(),

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE T_USER
ADD CONSTRAINT unique_email UNIQUE (email);