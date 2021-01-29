package org.leedh.user.service;

import lombok.extern.slf4j.Slf4j;
import org.leedh.user.dao.UserDao;
import org.leedh.user.vo.EmpDetailVO;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService, AuthenticationFailureHandler, AuthenticationSuccessHandler {

    private final UserDao userDao;

    @Autowired
    public UserLoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(EmpVO empVO) throws UsernameNotFoundException {
        // 최종적으로 리턴해야할 객체
        EmpDetailVO empDetails = new EmpDetailVO();

        // 사용자 정보 select
        EmpVO empinfo = userDao.selectEmpInfo(empVO);

        // 사용자 정보 없으면 예외 처리
        if (empinfo == null) {
            return null;

            // 사용자 정보 있을 경우 로직 전개 (userDetails에 데이터 넣기)
        } else {
            empDetails.setEmpEmail(empinfo.getEmpEmail());
            empDetails.setEmpPw(empinfo.getEmpPw());
            empDetails.setEmpNm(empinfo.getEmpNm());

            // 사용자 권한 select해서 받아온 List<String> 객체 주입
            empDetails.setAuthorities(userDao.selectEmpAuthOne(empVO));
        }

        return empDetails;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");

        } else if (exception instanceof LockedException) {
            request.setAttribute("loginFailMsg", "잠긴 계정입니다.");

        } else if (exception instanceof DisabledException) {
            request.setAttribute("loginFailMsg", "비활성화된 계정입니다.");

        } else if (exception instanceof AccountExpiredException) {
            request.setAttribute("loginFailMsg", "만료된 계정입니다.");

        } else if (exception instanceof CredentialsExpiredException) {
            request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");

        } else if (exception instanceof AuthenticationServiceException) {
            request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
        }

        // 로그인 페이지로 다시 포워딩
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/user/login");
        dispatcher.forward(request, response);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // IP , 세션 ID
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        log.info("IP: " + web.getRemoteAddress());
        log.info("Session ID: " + web.getSessionId());

        // 인증 ID
        log.info("name: " + authentication.getName());

        // 권한
        List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
        log.info("권한: ");

        for (GrantedAuthority grantedAuthority : authList) {
            log.info(grantedAuthority.getAuthority() + " ");
        }

        log.info("--------------------------------------------------------------------- ");

        // 패스워드 정보 지워주기
        EmpDetailVO userDetails = (EmpDetailVO) authentication.getPrincipal();
        if (userDetails != null) {
            userDetails.setEmpPw(null);
        }

        // 디폴트 URI
        String uri = "/";

        // 강제 인터셉트 당했을 경우의 데이터 get
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // 로그인 버튼 눌러 접속했을 경우의 데이터 get
        String prevPage = (String) request.getSession().getAttribute("prevPage");

        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        // null이 아니라면 강제 인터셉트 당했다는 것
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
            // ""가 아니라면 직접 로그인 페이지로 접속한 것
        } else if (prevPage != null && !prevPage.equals("")) {
            uri = prevPage;
        }

        // 세 가지 케이스에 따른 URI 주소로 리다이렉트
        response.sendRedirect(uri);

    }


}
