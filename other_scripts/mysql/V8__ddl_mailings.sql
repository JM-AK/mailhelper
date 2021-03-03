DROP TABLE IF EXISTS mailings;

CREATE TABLE mailings
(
    id                INT(11) NOT NULL AUTO_INCREMENT,
    company_name      VARCHAR(50) DEFAULT NULL,
    company_legalname VARCHAR(50) DEFAULT NULL,
    CONSTRAINT PK_Mailings__id PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
