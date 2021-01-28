package org.leedh.user.service;

import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private final UserDao userDao;

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
