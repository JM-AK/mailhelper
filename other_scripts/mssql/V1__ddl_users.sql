DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT          NOT NULL identity (1,1),
    username   NVARCHAR(50) NOT NULL,
    password   NVARCHAR(80) NOT NULL,
    first_name NVARCHAR(50) NOT NULL,
    last_name  NVARCHAR(50) NOT NULL,
    email      NVARCHAR(50) NOT NULL,
    phone      NVARCHAR(15) NOT NULL,
    CONSTRAINT PK_Users__id PRIMARY KEY (id)
);

