CREATE TABLE IF NOT EXISTS T_COMPANY
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    pan_number  VARCHAR(50) NOT NULL,
    association_name VARCHAR(255) NOT NULL,
    association_number VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL default current_timestamp(),

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;