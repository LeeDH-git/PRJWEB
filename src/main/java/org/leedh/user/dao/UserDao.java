package org.leedh.user.dao;

import org.leedh.user.vo.EmpVO;

import java.util.List;

public interface UserDao {
    // 회원가입
    void register(EmpVO empVO) throws Exception;

    // 회원 정보 존재 확인
    Integer selectEmpInfoCount(String empEmail);

    // 회원정보 검색
    EmpVO selectEmpInfoSearch(String empEmail);

    // 사용자 권한 검색(1명)
    List<String> selectEmpAuth(String empEmail);

}
