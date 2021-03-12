SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS file_info_metadata;

CREATE TABLE file_info_metadata
(
    hash VARCHAR(250) NOT NULL,
    file_name VARCHAR(250) NOT NULL,
    sub_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (hash)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;