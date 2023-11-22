CREATE TABLE orders (
  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
  date_time timestamp(0) NOT NULL,
  status varchar(255) NOT NULL,
  PRIMARY KEY (id)
)