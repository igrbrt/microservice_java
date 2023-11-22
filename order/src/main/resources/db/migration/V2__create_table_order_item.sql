CREATE TABLE order_items (
  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
  description varchar(255) DEFAULT NULL,
  amount int NOT NULL,
  order_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id)
)