package org.leedh.user.controller;

import org.leedh.user.service.UserService;
import org.leedh.user.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 페이지
    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String registerGET() throws Exception {
        return "/user/register";
    }

//    // 회원가입 처리
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String register(EmpVO empVO, RedirectAttributes rttr) throws Exception {
//        String pw = BCrypt.hashpw(empVO.getEmpPw(), BCrypt.gensalt());
//        empVO.setEmpPw(pw);
//        userService.register(empVO);
//        rttr.addFlashAttribute("Message", "REGISTERED");
//
//        return "redirect:/user/login";
//    }

    // 회원가입 등록 요청
    @RequestMapping(value = "/registerCheck", method = {RequestMethod.POST})
    public String register(@ModelAttribute EmpVO empVO, Model model) throws Exception {

        // 회원정보 및 디폴트 권한 입력
        boolean result = userService.register(empVO);

        if (result) {
            model.addAttribute("loginMsg", "회원 가입이 완료되었습니다. 로그인해주세요");
            return "/user/login";

        } else {
            model.addAttribute("registerUserMsg", "이미 존재하는 아이디입니다.");
            return "/user/register";
        }
    }

    // 회원정보 조회 요청
    @RequestMapping("/user/empCheck")
    public String empCheck(Principal prin, Model model) {

        EmpVO empVO = userService.selectEmpInfoSearch(prin.getName());
        model.addAttribute("empVO", empVO);
        return "/user/empCheck";
    }


    // 로그인 화면 요청
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
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
