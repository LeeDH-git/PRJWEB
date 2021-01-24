package org.leedh.user.service;

import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpAdminVO;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // 회원가입 처리
    @Override
    public void register(EmpVO empVO) throws Exception {
        userDao.register(empVO);
    }


    @Override
    public EmpVO login(LoginDTO loginDTO) throws Exception {
        return userDao.login(loginDTO);
    }
}
