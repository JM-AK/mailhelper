package ru.dv.mailhelper.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "upload_folder")
    private String uploadFolder;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "messageItem")
    @JsonIgnore
    private List<Attachment> attachmentList;

    public void addAttachment(Attachment attachment) {
        if (attachmentList == null) {
            attachmentList = new ArrayList<>();
        }
        attachmentList.add(attachment);
    }



}
