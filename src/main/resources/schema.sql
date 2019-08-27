CREATE TABLE person
(
    oib varchar(11) NOT NULL,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) DEFAULT NULL,
    PRIMARY KEY (oib)
);
