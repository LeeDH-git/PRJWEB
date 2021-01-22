package org.leedh.controller;

import lombok.extern.slf4j.Slf4j;
import org.leedh.service.loginService;
import org.leedh.vo.EmpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


@Controller
@RequestMapping("/*")
public class CommonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private loginService service;

    @Autowired
    public CommonController(loginService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(EmpVO empVO, HttpServletRequest req, RedirectAttributes rttr) {
        logger.info("로그인");

        HttpSession session = req.getSession();
        empVO login =
    }


}
