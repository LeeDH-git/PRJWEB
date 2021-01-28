package org.leedh.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("login") == null) {
            log.info("현재 사용자는 로그인되지 않았습니다.");
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
