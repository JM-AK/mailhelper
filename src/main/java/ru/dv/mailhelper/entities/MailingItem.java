package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dv.mailhelper.enums.MsgAddressType;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mailing_items")
public class MailingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "shortName", column = @Column(name = "company_name"))
    private Company company;

    @ElementCollection
    @CollectionTable(name = "mailing-items_contacts_mapping",
                    joinColumns = @JoinColumn(name = "mailing-item_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "contact_email")
    @Column(name = "address_type")
    private Map<Contact, MsgAddressType> msgAddressMap;

}
