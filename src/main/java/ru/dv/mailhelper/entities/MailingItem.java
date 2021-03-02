package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

//    private Map<Contact, > contactItemMap;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "order_item_mapping",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
//    @MapKeyJoinColumn(name = "seller_id")
//    private Map<Seller, Item> sellerItemMap;

}
