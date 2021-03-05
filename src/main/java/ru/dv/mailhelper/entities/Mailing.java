package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dv.mailhelper.enums.MsgAddressType;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mailings")
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "shortName", column = @Column(name = "company_name")),
                         @AttributeOverride(name = "fullName", column = @Column(name = "company_legalname"))})
    private Company company;

    @ElementCollection
    @CollectionTable(name = "mailings_contacts_mapping",
                    joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "contact_id")
    @Column(name = "address_type")
    @Enumerated(EnumType.STRING)
    private Map<Contact, MsgAddressType> msgAddressMap;

    @Transient
    private String subject;

    @Transient
    private String body;

    @Transient
    private String fileName;

}
