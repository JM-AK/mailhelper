DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT(11) NOT NULL AUTO_INCREMENT,
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(80) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    phone      VARCHAR(15) NOT NULL,
    CONSTRAINT PK_Users__id PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

