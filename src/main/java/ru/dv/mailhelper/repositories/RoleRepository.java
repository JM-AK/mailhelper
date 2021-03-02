package ru.dv.mailhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByName(String theRoleName);
}
