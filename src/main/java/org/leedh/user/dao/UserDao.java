package org.leedh.user.dao;

import org.leedh.user.vo.EmpAdminVO;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;

public interface UserDao {
    // 회원가입
    void register(EmpVO empVO) throws Exception;

    // 로그인
    EmpVO login(LoginDTO loginDTO) throws Exception;
}
