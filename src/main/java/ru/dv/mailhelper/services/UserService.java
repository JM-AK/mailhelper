package ru.dv.mailhelper.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dv.mailhelper.entities.SystemUser;
import ru.dv.mailhelper.entities.User;

public interface UserService extends UserDetailsService {
   User findByUsername(String username);
   boolean save(SystemUser systemUser);
}
