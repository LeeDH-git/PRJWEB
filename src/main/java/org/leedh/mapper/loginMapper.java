package org.leedh.mapper;

import org.leedh.vo.EmpVO;

public interface loginMapper {

    void register(EmpVO empVO);

    EmpVO login(EmpVO empVO);

}
