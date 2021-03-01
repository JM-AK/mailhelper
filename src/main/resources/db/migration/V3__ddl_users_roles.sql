DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id INT(11) NOT NULL,
    role_id INT(11) NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_USERS_ROLES__USERS__ID FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_USERS_ROLES__ROLES__ID FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ;
