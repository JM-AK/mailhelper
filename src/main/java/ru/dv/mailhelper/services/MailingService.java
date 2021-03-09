package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.entities.Contact;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.enums.MsgAddressType;
import ru.dv.mailhelper.repositories.MailingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MailingService {
    private MailingRepository mailingRepository;

    @Autowired
    public void setMailingRepository(MailingRepository mailingRepository) {
        this.mailingRepository = mailingRepository;
    }

    public List<Mailing> findAllMailing(){
        List<Mailing> mailingList = mailingRepository.findAll();
        return mailingList;
    }

    public Mailing saveMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }


    public Optional<Mailing> findById(Long id) {
        return mailingRepository.findById(id);
    }

    public Mailing updateContacts(Mailing mailing, Contact contact, String addressType) {

        if(addressType.equals(MsgAddressType.TO.toString())) {
            Collection<Contact> oldContact = mailing.getContactTo();
            oldContact.add(contact);
            mailing.setContactTo(oldContact);
        }
        if(addressType.equals(MsgAddressType.COPY.toString())) {
            Collection<Contact> oldContact = mailing.getContactTo();
            oldContact.add(contact);
            mailing.setContactCopy(oldContact);
        }
        if(addressType.equals(MsgAddressType.BCC.toString())) {
            Collection<Contact> oldContact = mailing.getContactTo();
            oldContact.add(contact);
            mailing.setContactBcc(oldContact);
        }
        return mailingRepository.save(mailing);
    }

}
