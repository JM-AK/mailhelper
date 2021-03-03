package ru.dv.mailhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
