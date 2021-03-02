package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "contacts_companies",
//            joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "contact_id"))
//    private List<Contact> contacts;
}
