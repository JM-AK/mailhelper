package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.entities.Contact;
import ru.dv.mailhelper.repositories.ContactMapper;

import java.util.*;

@Service
public class ContactService {
    private ContactMapper contactMapper;

    private Map<Long, Contact> contactMap = new HashMap<>();

    @Autowired
    public void setContactRepository(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    public void saveContact(Contact contact) {
        contactMap.put(contactMapper.save(contact), contact);
    }

    public Collection<Contact> findAllContacts() {
        if (contactMap.isEmpty()) {
            List<Contact> contactList = contactMapper.findAll();
            contactList.forEach(contact -> contactMap.put(contact.getId(),contact));
            return contactList;
        }
        return contactMap.values();
    }

    public Contact findById (Long id) {
        Contact c = contactMap.get(id);
        if (c == null) {
            c = contactMapper.findById(id);
        }
        return c;
    }

}
