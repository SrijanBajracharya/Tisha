CREATE TABLE IF NOT EXISTS T_ORDER
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    due_date    TIMESTAMP NOT NULL default current_timestamp(),
    status      VARCHAR(50) NOT NULL,
    comment     VARCHAR(500) NULL,
    customer_id BIGINT(20) NOT NULL,
    created_by  BIGINT(20) NOT NULL,
    created_date TIMESTAMP NOT NULL default current_timestamp(),
    last_modified_by BIGINT(20) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL default current_timestamp(),

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS T_ITEMS
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    rate        decimal(19,2) NOT NULL,
    order_id    BIGINT(20) NOT NULL,
    name        VARCHAR(500) NOT NULL,
    purity      decimal(19,2) NOT NULL,
    weight      decimal(19,2) NOT NULL,
    due_date    TIMESTAMP NOT NULL default current_timestamp(),
    status      VARCHAR(50) NOT NULL,
    comment     VARCHAR(500) NULL,
    type        VARCHAR(50) NOT NULL,
    created_by  BIGINT(20) NOT NULL,
    created_date TIMESTAMP NOT NULL default current_timestamp(),
    last_modified_by BIGINT(20) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL default current_timestamp(),

    PRIMARY KEY (id),
    CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES T_ORDER(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS T_STONE
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    type_id     BIGINT(20) NOT NULL,
    item_id     BIGINT(20) NOT NULL,
    price       decimal(19,2) NOT NULL,
    quantity    int(10) NOT NULL,
    created_by  BIGINT(20) NOT NULL,
    created_date TIMESTAMP NOT NULL default current_timestamp(),
    last_modified_by BIGINT(20) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL default current_timestamp(),

    PRIMARY KEY (id),
    CONSTRAINT fk_item_stone FOREIGN KEY (item_id) REFERENCES T_ITEMS(id),
    CONSTRAINT fk_stone_type_stone FOREIGN KEY (type_id) REFERENCES T_STONE_TYPE(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;