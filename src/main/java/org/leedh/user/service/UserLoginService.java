package org.leedh.user.service;

import org.leedh.user.vo.EmpVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserLoginService {

     UserDetails loadUserByUsername(EmpVO empVO) throws UsernameNotFoundException;

}
