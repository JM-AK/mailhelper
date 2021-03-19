DROP TABLE IF EXISTS messages;

CREATE TABLE messages
(
    id        BIGINT(11) NOT NULL AUTO_INCREMENT,
    create_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id   BIGINT(11) NOT NULL,
    status_id BIGINT(11) NOT NULL,
    sent_at   TIMESTAMP,

    CONSTRAINT PK_Messages__id PRIMARY KEY (id),
    CONSTRAINT FK_Messages__Users__id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_Messages__Messages_statuses__id FOREIGN KEY (status_id)
        REFERENCES messages_statuses (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4;
