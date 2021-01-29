package org.leedh.user.service;

import org.leedh.user.vo.EmpVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // 회원가입 처리
    void register(EmpVO empVO) throws Exception;

    // 회원 정보 확인
    Integer selectUser(EmpVO empVO) throws Exception;

    // 회원정보 검색
    EmpVO selectEmpInfo(EmpVO empVO);

}
