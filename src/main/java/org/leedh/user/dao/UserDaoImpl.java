package org.leedh.user.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
@Transactional
public class UserDaoImpl implements UserDao {

    private static final String NAMESPACE = "org.leedh.mapper.UserMapper";

    private final SqlSession sqlSession;

    @Autowired
    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 회원가입 처리
    @Override
    public void register(EmpVO empVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".register", empVO);
        log.debug("register error : " + empVO);
    }

    // 회원 정보 존재 확인
    @Override
    public Integer selectUser(EmpVO empVO) {
        return sqlSession.selectOne(NAMESPACE + ".selectUser", empVO);

    }
    // 회원정보 검색
    @Override
    public EmpVO selectEmpInfo(EmpVO empVO) {
        return sqlSession.selectOne(NAMESPACE + ".selectEmpInfo", empVO);
    }

    // 사용자 권한 검색(1명)
    @Override
    public List<String> selectEmpAuthOne(EmpVO empVO) {
        return sqlSession.selectOne(NAMESPACE+".selectEmpAuthOne",empVO);
    }
}

