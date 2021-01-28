package org.leedh.user.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    // 로그인 처리
    @Override
    public EmpVO login(LoginDTO loginDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
    }

}

