package org.leedh.user.service;

import lombok.extern.slf4j.Slf4j;
import org.leedh.common.util.BCryptPwEncodingUtil;
import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpDetailVO;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserDetailsService  {

    private final UserDao userDao;

    @Autowired
    public UserLoginServiceImpl(UserDao userDao ) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String empEmail) {
        // 최종적으로 리턴해야할 객체
        EmpDetailVO empDetails = new EmpDetailVO();

        // 사용자 정보 select
        EmpVO empInfo = userDao.selectEmpInfoSearch(empEmail);

        // 사용자 정보 없으면 예외 처리
        if (empInfo == null) {
            return null;

            // 사용자 정보 있을 경우 로직 전개 (empDetails 데이터 넣기)
        } else {
            empDetails.setEmpEmail(empInfo.getEmpEmail());
            empDetails.setEmpPw(empInfo.getEmpPw());
            empDetails.setEmpNm(empInfo.getEmpNm());

            // 사용자 권한 select해서 받아온 List<String> 객체 주입
            empDetails.setAuthorities(userDao.selectEmpAuth(empEmail));
        }

        return empDetails;
    }


}
