DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts
(
    id         INT NOT NULL IDENTITY(1,1),
    first_name NVARCHAR(50) DEFAULT NULL,
    last_name  NVARCHAR(50) DEFAULT NULL,
    email      NVARCHAR(50) DEFAULT NULL,
    phone      NVARCHAR(50) DEFAULT NULL,
    CONSTRAINT PK_Contacts__id PRIMARY KEY (id)
);
