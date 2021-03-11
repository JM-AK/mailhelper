package ru.dv.mailhelper.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SortedMapType;
import ru.dv.mailhelper.enums.MsgAddressType;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mailings_contacts_to_mapping",
                    joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactTo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mailings_contacts_copy_mapping",
            joinColumns = @JoinColumn(name = "mailing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
    )
    private Collection<Contact> contactCopy;

    @ManyToMany(fetch = FetchType.LAZY)
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
