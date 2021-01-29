package org.leedh.user.controller;

import org.leedh.user.service.UserService;
import org.leedh.user.vo.EmpVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() throws Exception {
        return "/user/register";
    }

    // 회원가입 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(EmpVO empVO, RedirectAttributes rttr) throws Exception {
        String pw = BCrypt.hashpw(empVO.getEmpPw(), BCrypt.gensalt());
        empVO.setEmpPw(pw);
        userService.register(empVO);
        rttr.addFlashAttribute("Message", "REGISTERED");

        return "redirect:/user/login";
    }

    // 로그인 화면 요청
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(HttpServletRequest req) throws Exception {

        // 사용자가 서버에 요청하는 시점에 가지고 있는 URI 정보
        String uri = req.getHeader("Referer");

        // 요청 시점의 사용자 URI 정보를 Session의 Attribute에 담아서 전달(잘 지워줘야 함)
        // 로그인이 틀려서 다시 하면 요청 시점의 URI가 로그인 페이지가 되므로 조건문 설정
        if (uri != null && !uri.contains("/user/login")) {
            req.getSession().setAttribute("prevPage", req.getHeader("Referer"));
        }
        return "/user/login";
    }


}
