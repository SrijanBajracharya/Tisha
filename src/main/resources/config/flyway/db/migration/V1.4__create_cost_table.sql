CREATE TABLE IF NOT EXISTS T_COST
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    jyala       decimal(19,2) NOT NULL,
    jarti_quantity  decimal(19,2) NOT NULL,
    item_id     BIGINT(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_cost_item FOREIGN KEY (item_id) REFERENCES T_ITEMS(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
