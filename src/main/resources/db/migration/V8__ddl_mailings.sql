DROP TABLE IF EXISTS mailings;

CREATE TABLE mailings
(
    id                INT NOT NULL IDENTITY (1,1),
    company_name      NVARCHAR(50) DEFAULT NULL,
    company_legalname NVARCHAR(50) DEFAULT NULL,
    CONSTRAINT PK_Mailings__id PRIMARY KEY (id)
);
