package ru.dv.mailhelper.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_item_id")
    private MessageItem messageItem;

    @Column(name = "path")
    private String path;
}