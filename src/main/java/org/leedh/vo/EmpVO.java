package org.leedh.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpVO {
 /*
   사원번호               empNo
   사원명                 empNm
   사원이메일             empEmail
   직원구분코드            pjtEmpDivC
   직군코드               pjtJgC
   사원휴대폰연락처        empPhoneNo
   사원비상연락처          empEmerNo
   사원입사일             empJoinDate
   사원부서코드            pjtDeptC // 현재 사용 x
   기술등급코드            pjtLvC
   직위코드               pjtPosC
   직책코드               pjtDutyC
*/

    private String empNo;
    private String empNm;
    private String empEmail;
    private String pjtEmpDivC;
    private String pjtJgC;
    private String empPhoneNo;
    private String empEmerNo;
    private String empJoinDate;
    private String pjtDeptC;
    private String pjtLvC;
    private String pjtPosC;
    private String pjtDutyC;


}
