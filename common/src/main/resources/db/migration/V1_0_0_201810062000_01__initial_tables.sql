CREATE TABLE user (
  id         VARCHAR(255) NOT NULL,
  version    INT          NOT NULL,
  created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `group` (
  id         VARCHAR(255) NOT NULL,
  version    INT          NOT NULL,
  created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  name       VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_group (
  user_id  VARCHAR(255) NOT NULL,
  group_id VARCHAR(255) NOT NULL,
  KEY user_group_user_id_fk (user_id),
  CONSTRAINT user_group_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
  KEY user_group_group_id_fk (group_id),
  CONSTRAINT user_group_group_id_fk FOREIGN KEY (group_id) REFERENCES `group` (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE currency (
  code    VARCHAR(4)   NOT NULL,
  name    VARCHAR(100) NOT NULL,
  country VARCHAR(100) NOT NULL,
  PRIMARY KEY (code)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE balance (
  id            VARCHAR(255)   NOT NULL,
  version       INT            NOT NULL,
  created_on    DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on    DATETIME DEFAULT CURRENT_TIMESTAMP,
  amount        DECIMAL(10, 2) NOT NULL,
  currency_code VARCHAR(4)     NOT NULL,
  group_id      VARCHAR(255)   NOT NULL,
  user_id       VARCHAR(255)   NOT NULL,
  PRIMARY KEY (id),
  KEY balance_user_id_fk (user_id),
  CONSTRAINT balance_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
  KEY balance_group_id_fk (group_id),
  CONSTRAINT balance_group_id_fk FOREIGN KEY (group_id) REFERENCES `group` (id),
  KEY balance_currency_code_fk (currency_code),
  CONSTRAINT balance_currency_code_fk FOREIGN KEY (currency_code) REFERENCES currency (code)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE currency_rate (
  id                 VARCHAR(255)   NOT NULL,
  version            INT            NOT NULL,
  created_on         DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on         DATETIME DEFAULT CURRENT_TIMESTAMP,
  currency_from_code VARCHAR(255)   NOT NULL,
  currency_to_code   VARCHAR(255)   NOT NULL,
  rate               DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  KEY currency_rate_currency_from_code_fk (currency_from_code),
  CONSTRAINT currency_rate_currency_from_code_fk FOREIGN KEY (currency_from_code) REFERENCES currency (code),
  KEY currency_rate_currency_to_code_fk (currency_to_code),
  CONSTRAINT currency_rate_currency_to_code_fk FOREIGN KEY (currency_to_code) REFERENCES currency (code)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE category (
  id         VARCHAR(255) NOT NULL,
  version    INT          NOT NULL,
  created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  name       VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE bill (
  id            VARCHAR(255)   NOT NULL,
  version       INT            NOT NULL,
  created_on    DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on    DATETIME DEFAULT CURRENT_TIMESTAMP,
  description   VARCHAR(100)   NOT NULL,
  amount        DECIMAL(10, 2) NOT NULL,
  currency_code VARCHAR(4)     NOT NULL,
  group_id      VARCHAR(255)   NOT NULL,
  shared_type   VARCHAR(100)   NOT NULL,
  PRIMARY KEY (id),
  KEY bill_currency_code_fk (currency_code),
  CONSTRAINT bill_currency_code_fk FOREIGN KEY (currency_code) REFERENCES currency (code),
  KEY bill_group_id_fk (group_id),
  CONSTRAINT bill_group_id_fk FOREIGN KEY (group_id) REFERENCES `group` (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_bill (
  bill_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  KEY user_bill_bill_id_fk (bill_id),
  CONSTRAINT user_bill_bill_id_fk FOREIGN KEY (bill_id) REFERENCES bill (id),
  KEY user_bill_user_id_fk (user_id),
  CONSTRAINT user_bill_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE bill_item (
  id          VARCHAR(255)   NOT NULL,
  version     INT            NOT NULL,
  created_on  DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on  DATETIME DEFAULT CURRENT_TIMESTAMP,
  title       VARCHAR(100)   NOT NULL,
  amount      DECIMAL(10, 2) NOT NULL,
  category_id VARCHAR(255)   NOT NULL,
  bill_id     VARCHAR(255)   NOT NULL,
  PRIMARY KEY (id),
  KEY bill_item_category_id_fk (category_id),
  CONSTRAINT bill_item_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id),
  KEY bill_item_bill_id_fk (bill_id),
  CONSTRAINT bill_item_bill_id_fk FOREIGN KEY (bill_id) REFERENCES bill (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE share (
  id         VARCHAR(255) NOT NULL,
  version    INT          NOT NULL,
  created_on DATETIME       DEFAULT CURRENT_TIMESTAMP,
  updated_on DATETIME       DEFAULT CURRENT_TIMESTAMP,
  user_id    VARCHAR(255) NOT NULL,
  bill_id    VARCHAR(255) NOT NULL,
  amount     DECIMAL(10, 2) DEFAULT NULL,
  percent    DECIMAL(10, 2) DEFAULT NULL,
  share      DECIMAL(10, 2) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY share_user_id_fk (user_id),
  CONSTRAINT share_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
  KEY share_bill_id_fk (bill_id),
  CONSTRAINT share_bill_id_fk FOREIGN KEY (bill_id) REFERENCES bill (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE payer (
  id         VARCHAR(255)   NOT NULL,
  version    INT            NOT NULL,
  created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  user_id    VARCHAR(255)   NOT NULL,
  amount     DECIMAL(10, 2) NOT NULL,
  bill_id    VARCHAR(255)   NOT NULL,
  PRIMARY KEY (id),
  KEY payer_user_id_fk (user_id),
  CONSTRAINT payer_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
  KEY payer_bill_id_fk (bill_id),
  CONSTRAINT payer_bill_id_fk FOREIGN KEY (bill_id) REFERENCES bill (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;