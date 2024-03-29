package ru.dv.mailhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.Mailing;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
