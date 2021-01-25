package org.leedh.user.controller;

import org.leedh.user.service.UserService;
import org.leedh.user.vo.EmpVO;
import org.leedh.user.vo.LoginDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(EmpVO empVO, RedirectAttributes rttr) throws Exception {
        String pw = BCrypt.hashpw(empVO.getEmpPw(), BCrypt.gensalt());
        empVO.setEmpPw(pw);
        userService.register(empVO);
        rttr.addFlashAttribute("Message : ", "register success");

        return "redirect:/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
        EmpVO empVO = userService.login(loginDTO);

        if (empVO == null || !BCrypt.checkpw(loginDTO.getEmpPw(), empVO.getEmpPw())) {
            return;
        }

        model.addAttribute("EMP", empVO);
    }

}
