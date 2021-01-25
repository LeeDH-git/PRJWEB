package org.leedh.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        assert modelAndView != null;
        ModelMap modelMap = modelAndView.getModelMap();
        Object empVO = modelMap.get("EMP");

        if (empVO != null) {
            log.info("login success");
            httpSession.setAttribute(LOGIN, empVO);
            response.sendRedirect("/");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute(LOGIN) != null) {
            log.info("login data delete");
            httpSession.removeAttribute(LOGIN);
        }
        return true;
    }


}
