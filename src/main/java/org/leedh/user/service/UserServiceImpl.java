package org.leedh.user.service;

import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Integer selectUser(String empEmail) {
        return userDao.selectUser(empEmail);
    }

    @Override
    public EmpVO selectEmpInfo(String empEmail) {
        return userDao.selectEmpInfo(empEmail);
    }

    @Override
    public List<String> selectEmpAuthOne(String empEmail) {
        return selectEmpAuthOne(empEmail);
    }
}
