package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message_items")
@Data
@NoArgsConstructor
public class MessageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mailing_id")
    private Mailing mailing;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;



    
}
