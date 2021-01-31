package org.leedh.user.service;

import org.leedh.common.util.BCryptPwEncodingUtil;
import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    BCryptPwEncodingUtil pwEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPwEncodingUtil pwEncoder) {
        this.userDao = userDao;
        this.pwEncoder = pwEncoder;
    }

    // 회원가입 처리
    @Override
    public boolean register(EmpVO empVO) throws Exception {

        // 존재하는 ID 여부 확인
        Integer userCount = userDao.selectEmpInfoCount(empVO.getEmpEmail());

        if (userCount > 0) {
            return false;
        } else {
            // EmpVO 내용 중 패스워드를 암호화시켜서 바꿔줌
            empVO.setEmpPw(pwEncoder.encode(empVO.getEmpPw()));

            // 회원정보 입력
            userDao.register(empVO);

            return true;
        }

    }

    @Override
    public Integer selectEmpInfoCount(String empEmail) {
        return userDao.selectEmpInfoCount(empEmail);
    }

    @Override
    public EmpVO selectEmpInfoSearch(String empEmail) {
        return userDao.selectEmpInfoSearch(empEmail);
    }

    @Override
    public List<String> selectEmpAuth(String empEmail) {
        return selectEmpAuth(empEmail);
    }
}
