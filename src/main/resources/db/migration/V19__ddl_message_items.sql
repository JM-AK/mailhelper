SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS message_items;

CREATE TABLE message_items
(
    id            BIGINT(11) NOT NULL AUTO_INCREMENT,
    mailing_id    BIGINT(11) NOT NULL,
    message_id    BIGINT(11) NOT NULL,
    subject       VARCHAR(50)  DEFAULT NULL,
    body          VARCHAR(255) DEFAULT NULL,
    upload_folder VARCHAR(255) DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_Message_items__Mailings__id FOREIGN KEY (mailing_id)
        REFERENCES mailings (id),
    CONSTRAINT FK_Message_items__Messages__id FOREIGN KEY (message_id)
        REFERENCES messages (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;