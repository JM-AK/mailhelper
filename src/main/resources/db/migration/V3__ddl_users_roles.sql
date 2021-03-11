DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id BIGINT(11) NOT NULL,
    role_id BIGINT(11) NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_Users_roles__Users__id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_Users_roles__Roles__id FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
