CREATE TABLE currency(
  id VARCHAR(3) NOT NULL,
  name VARCHAR(100) NOT NULL,
  country VARCHAR(100),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;