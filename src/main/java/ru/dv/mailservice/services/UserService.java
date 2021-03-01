package ru.dv.mailservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dv.mailservice.entities.SystemUser;
import ru.dv.mailservice.entities.User;

public interface UserService extends UserDetailsService {
   User findByUserName(String username);
   boolean save(SystemUser systemUser);
}
