package org.leedh.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserLoginService {

     UserDetails loadUserByUsername(String empEmail) throws UsernameNotFoundException;

}
