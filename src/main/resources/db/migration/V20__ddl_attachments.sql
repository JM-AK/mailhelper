SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS attachments;

CREATE TABLE attachments
(
    id              BIGINT(11) NOT NULL AUTO_INCREMENT,
    message_item_id BIGINT(11) NOT NULL,
    path            VARCHAR(250) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_Attachmemts__Message_items__id FOREIGN KEY (message_item_id)
        REFERENCES message_items (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;