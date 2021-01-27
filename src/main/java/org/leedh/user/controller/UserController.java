package org.leedh.user.controller;

import org.leedh.user.service.UserService;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
        rttr.addFlashAttribute("Message", "register success");

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @RequestMapping(value = "/login", method = RequestMethod.GET)
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

        model.addAttribute("EMP", empVO);
    }

}
