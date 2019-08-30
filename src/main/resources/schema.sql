CREATE TABLE person
(
    oib VARCHAR(11) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (oib)
);
