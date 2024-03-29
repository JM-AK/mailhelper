DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts
(
    id         INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) DEFAULT NULL,
    last_name  VARCHAR(50) DEFAULT NULL,
    email      VARCHAR(50) DEFAULT NULL,
    phone      VARCHAR(50) DEFAULT NULL,
    CONSTRAINT PK_Contacts__id PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
