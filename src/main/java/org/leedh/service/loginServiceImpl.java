package org.leedh.service;

import org.leedh.mapper.loginMapper;
import org.leedh.vo.EmpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final loginMapper mapper;
    @Autowired
    public loginServiceImpl(loginMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void register(EmpVO empVO) throws Exception {
        logger.info("회원가입");
        mapper.register(empVO);
    }

    @Override
    public EmpVO login(EmpVO empVO) throws Exception {
        logger.info("로그인");
        return mapper.login(empVO);
    }
}
