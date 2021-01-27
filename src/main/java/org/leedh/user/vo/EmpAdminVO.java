package org.leedh.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EmpAdminVO {
    private int empNo;
    private LocalDateTime loginDate;
    private LocalDateTime joinDate;
    private LocalDateTime usChangePwDate;
    private int loginFailCount;
    private LocalDateTime listAccessDate;
    private String pjtDutyC;
    private String pjtJgC;
    private String pjtDeptC;

}
