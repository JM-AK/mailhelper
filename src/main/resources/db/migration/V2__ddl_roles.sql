DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id                    INT NOT NULL IDENTITY(1,1),
  name                  NVARCHAR(50) DEFAULT NULL,
  CONSTRAINT PK_Roles__id PRIMARY KEY (id)
) ;
