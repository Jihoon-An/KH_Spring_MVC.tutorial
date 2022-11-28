package com.kh.netflix.controller;

import com.kh.netflix.repository.MovieRepository;
import com.kh.netflix.dto.MoviesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/netflix/")
public class NetflixController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HttpSession session;

    @RequestMapping("toInput")
    public String toInput() {
        return "netflix/input";
    }

    @RequestMapping("insert")
    public String insert(MoviesDTO movieDto) {
        try {
            movieRepository.insert(movieDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }

    @RequestMapping("getList")
    public String selectAll(Model model) throws SQLException {

        List<MoviesDTO> movies = movieRepository.selectAll();

        model.addAttribute("movies", movies);

        return "netflix/output";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

}
