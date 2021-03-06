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

    @Override
    public Integer selectEmpInfoCount(String empEmail) {
        return sqlSession.selectOne(NAMESPACE + ".selectEmpInfoCount", empEmail);
    }

    @Override
    public EmpVO selectEmpInfoSearch(String empEmail) {
        return sqlSession.selectOne(NAMESPACE + ".selectEmpInfoSearch", empEmail);
    }

    @Override
    public List<String> selectEmpAuth(String empEmail) {
        return sqlSession.selectOne(NAMESPACE + ".selectEmpAuth", empEmail);
    }
}