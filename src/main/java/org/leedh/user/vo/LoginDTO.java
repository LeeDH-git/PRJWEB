package org.leedh.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class LoginDTO {
    private String empEmail;
    private String empPw;
    private boolean cookie;
}