package org.leedh.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class EmpAdminVO {
    private String empNo;
    private LocalDateTime loginDate;
    private LocalDateTime joinDate;
    private LocalDateTime usChangePwDate;
    private int loginFailCount;
    private LocalDateTime listAccessDate;
}
