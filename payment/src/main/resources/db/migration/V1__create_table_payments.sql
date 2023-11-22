CREATE TABLE payments (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    value decimal(19,2) NOT NULL,
    name varchar(100) DEFAULT NULL,
    card_number varchar(19) DEFAULT NULL,
    expiration_date varchar(7) DEFAULT NULL,
    card_code varchar(3) DEFAULT NULL,
    status varchar(255) NOT NULL,
    payment_method_id bigint NOT NULL,
    order_id bigint NOT NULL,
 PRIMARY KEY (id)
);