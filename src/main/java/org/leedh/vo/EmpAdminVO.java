package org.leedh.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EmpAdminVO {

    private String empNo;
    private String empNm;
    private String empEmail;
    private String empPw;
    private String pjtEmpDivC;
    private String pjtJgC;
    private Date regDate;
    private String usChangePwDate;
    private int loginFailCount;
    private String listAccessDate;


}
