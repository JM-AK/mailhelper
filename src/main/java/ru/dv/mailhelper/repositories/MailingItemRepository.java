package ru.dv.mailhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.MailingItem;

@Repository
public interface MailingItemRepository extends JpaRepository<MailingItem, Long> {
}
