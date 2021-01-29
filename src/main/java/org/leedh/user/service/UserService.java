package org.leedh.user.service;

import org.leedh.user.vo.EmpVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // 회원가입 처리
    boolean register(EmpVO empVO) throws Exception;

    // 회원 정보 존재 확인
    Integer selectEmpInfoCount(String empEmail);

    // 회원정보 검색
    EmpVO selectEmpInfoSearch(String empEmail);

    // 사용자 권한 검색(1명)
    List<String> selectEmpAuth(String empEmail);


}
