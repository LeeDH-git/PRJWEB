package org.leedh.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
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
        sqlSession.insert(NAMESPACE + "register", empVO);
    }

    // 로그인 처리
    @Override
    public EmpVO login(LoginDTO loginDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "login", loginDTO);
    }
}

