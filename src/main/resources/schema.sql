CREATE TABLE book
(
  isbn varchar(11) NOT NULL ,
 title varchar(100) NOT NULL,
 author varchar(100) DEFAULT NULL,
 PRIMARY KEY (isbn)
);
