package org.leedh.user.controller;

import org.leedh.user.service.UserService;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 페이지
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO) throws Exception {
        return "/user/login";
    }

    // 로그인 처리
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public void loginCheck(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
        EmpVO empVO = userService.login(loginDTO);

        if (empVO == null || !BCrypt.checkpw(loginDTO.getEmpPw(), empVO.getEmpPw())) {
            return;
        }

        model.addAttribute("user", empVO);

    }
}
