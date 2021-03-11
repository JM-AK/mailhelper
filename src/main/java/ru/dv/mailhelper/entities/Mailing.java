package ru.dv.mailhelper.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SortedMapType;
import ru.dv.mailhelper.enums.MsgAddressType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mailings")
@DynamicUpdate
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "shortName", column = @Column(name = "company_name")),
                         @AttributeOverride(name = "fullName", column = @Column(name = "company_legalname"))})
    private Company company;


//    @ElementCollection
//    @CollectionTable(name = "mailings_contacts_mapping",
//            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id")
//    )
//    @MapKeyJoinColumn(name = "contact_id")
//    @Column(name = "address_type")
//    @Enumerated(EnumType.STRING)
//    @OrderColumn(name = "PK_MAILINGS_CONTACTS_MAPPING")
//    private Map<Contact, MsgAddressType> contactFields;


    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mailings_contacts_to_mapping",
                    joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactTo;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mailings_contacts_copy_mapping",
            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactCopy;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mailings_contacts_bcc_mapping",
            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactBcc;


    @OneToMany(mappedBy = "mailing")
    @JsonIgnore
    private List<MessageItem> messageItems;

    @Column(name = "create_at", nullable=false, updatable = false )
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;


}
