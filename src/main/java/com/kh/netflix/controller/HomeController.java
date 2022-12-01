package com.kh.netflix.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    HttpSession session;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping("/")
    public String home(Locale locale, Model model) throws Exception {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    @RequestMapping("/toSignUp")
    public String toSignUp() {
        return "netflix/signup";
    }

    @RequestMapping("/toLogIn")
    public String toLogIn() {
        return "netflix/login";
    }

    @RequestMapping("/toFile")
    public String toFile() {
        return "file";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(String message, MultipartFile[] files) throws IOException {

        // 경로 지정
        String realPath = session.getServletContext().getRealPath("upload");

        // 폴더 생성
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        for (MultipartFile file : files) {
            if (file.getOriginalFilename() == null) {
                continue; // file이 빈 파일이라면 패스
            }
            // 파일 이름 설정
            String orgName = file.getOriginalFilename();
            String sysName = UUID.randomUUID() + "_" + orgName;

            // 파일 복사
            file.transferTo(new File(filePath + "/" + sysName));
        }

        return "redirect:/";
    }

    @RequestMapping("/testFileUpload")
    public void testMethod(String message, MultipartFile profileImg){


        System.out.println(message);

    }

    @RequestMapping("/testCode")
    public void home() {
    System.out.println("test success!");
    }

    @RequestMapping("/insert")
    public String insert(String writer, String message) {
        System.out.println(writer + " : " + message);
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }
}
