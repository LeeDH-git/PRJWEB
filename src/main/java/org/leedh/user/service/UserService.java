package org.leedh.user.service;

import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // 회원가입 처리
    void register(EmpVO empVO) throws Exception;

    // 로그인 처리
    EmpVO login(LoginDTO loginDTO) throws Exception;

}
