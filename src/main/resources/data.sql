DROP TABLE IF EXISTS USER;
  
CREATE TABLE USER (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL
);

INSERT INTO USER (id, name, email,address) VALUES
  (1,'Lokesh','abc@gmail.com','pune'),
  (2,'Deja','xyz@gmail.com','pune');