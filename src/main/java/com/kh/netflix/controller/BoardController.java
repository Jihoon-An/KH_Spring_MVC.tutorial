package com.kh.netflix.controller;

import com.kh.netflix.dao.BoardRepository;
import com.kh.netflix.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BoardRepository boardRepository;


    @RequestMapping("toBoard")
    public String toBoard(Model model) throws SQLException {

        List<BoardDTO> boardList = boardRepository.selectAll();
        model.addAttribute("boardList", boardList);

        return "board/table";
    }

    @RequestMapping("toDetail")
    public String toDetail(Model model, int seq) throws SQLException {

        BoardDTO post = boardRepository.selectBySeq(seq);

        model.addAttribute("post", post);

        return "board/detail";
    }

    @RequestMapping("toPosting")
    public String toPosting() {
        return "board/posting";
    }

    @RequestMapping("posting")
    public String posting(BoardDTO board, String title, String content) throws SQLException {

        board.setWriter((String) session.getAttribute("loginId"));

        boardRepository.insert(board);

        return "redirect:/board/toBoard";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }
}
