DROP TABLE IF EXISTS mailings_contacts_to_mapping;

CREATE TABLE mailings_contacts_to_mapping
(
    mailing_id   BIGINT NOT NULL,
    contact_id   BIGINT NOT NULL,
    address_type VARCHAR(50) DEFAULT('TO'),

    PRIMARY KEY (mailing_id, contact_id, address_type),

    CONSTRAINT FK_MAILINGS_CONTACTS_TO_MAPPING__MAILINGS__ID FOREIGN KEY (mailing_id)
        REFERENCES mailings (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_MAILINGS_CONTACTS_TO_MAPPING__CONTACTS__ID FOREIGN KEY (contact_id)
        REFERENCES contacts (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION

)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
