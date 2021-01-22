package org.leedh.service;

import org.leedh.vo.EmpVO;

public interface loginService {
    void register(EmpVO empVO) throws Exception;

    EmpVO login(EmpVO empVO) throws Exception;
}
