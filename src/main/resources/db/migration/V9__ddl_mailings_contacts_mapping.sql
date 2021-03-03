DROP TABLE IF EXISTS mailings_contacts_mapping;

CREATE TABLE mailings_contacts_mapping
(
    mailing_id    INT          NOT NULL,
    contact_id NVARCHAR(50) NOT NULL,
    address_type  NVARCHAR(50) NOT NULL,
    PRIMARY KEY (mailing_id, contact_id, address_type),

    CONSTRAINT FK_MAILINGS_CONTACTS_MAPPING__MAILINGS__ID FOREIGN KEY (mailing_id)
        REFERENCES mailings (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_MAILINGS_CONTACTS_MAPPING__CONTACTS__ID FOREIGN KEY (contact_id)
        REFERENCES contacts (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION

);
