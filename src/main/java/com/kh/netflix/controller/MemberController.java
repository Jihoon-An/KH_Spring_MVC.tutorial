package com.kh.netflix.controller;

import com.kh.netflix.common.Common;
import com.kh.netflix.dao.MemberRepository;
import com.kh.netflix.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpSession session;

    @RequestMapping("/signUp")
    public String signUp(MemberDTO member) throws Exception {

        String beforePw = member.getPw();
        member.setPw(Common.getSHA512(beforePw));

        memberRepository.insert(member);

        return "/";
    }

    @RequestMapping("/dupleCheck")
    @ResponseBody
    public String dupleCheck(String id) throws Exception {

        MemberDTO member = memberRepository.selectById(id);

        if (member == null) {
            return "false";
        } else {
            return "true";
        }
    }

    @RequestMapping("/logIn")
    public String logIn(String id, String pw) throws Exception {

        MemberDTO member = memberRepository.selectById(id);
        if (Common.getSHA512(pw).equals(member.getPw())) {
            session.setAttribute("loginId", id);
            System.out.println("login Success!");
        } else {
            System.out.println("login Fail!");
        }

        return "redirect:/";
    }

    @RequestMapping("/toMypage")
    public String toMypage(Model model) throws Exception{

        String loginId = (String) session.getAttribute("loginId");
        MemberDTO member = memberRepository.selectById(loginId);

        model.addAttribute("member", member);

        return "member/mypage";
    }

    @RequestMapping("/logout")
    public String logout(){
        session.removeAttribute("loginId");

        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }
}
