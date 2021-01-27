package org.leedh.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpVO {
 /*
   사원번호         empNo;
   사원명           empNm;
   직원비밀번호      empPw;
   사원이메일        empEmail;
   사원휴대폰연락처   empPhoneNo;
   사원비상연락처     empEmerNo;
   사원입사일        empJoinDate;
   기술등급코드       pjtLvC;
   직위코드          pjtPosC;
*/

    private int empNo;
    private String empNm;
    private String empPw;
    private String empEmail;
    private String empPhoneNo;
    private String empEmerNo;
    private String empJoinDate;
    private String pjtLvC;
    private String pjtPosC;


}
