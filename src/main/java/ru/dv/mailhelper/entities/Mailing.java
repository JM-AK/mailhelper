package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortComparator;
import org.hibernate.type.SortedMapType;
import ru.dv.mailhelper.enums.MsgAddressType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
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

    @OneToMany
    @JoinTable(name = "mailings_contacts_to_mapping",
                    joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactTo;

    @OneToMany
    @JoinTable(name = "mailings_contacts_copy_mapping",
            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactCopy;

    @OneToMany
    @JoinTable(name = "mailings_contacts_bcc_mapping",
            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactBcc;



}
