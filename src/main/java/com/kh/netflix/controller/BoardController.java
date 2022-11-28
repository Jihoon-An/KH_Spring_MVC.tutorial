package com.kh.netflix.controller;

import com.kh.netflix.dto.FileDTO;
import com.kh.netflix.repository.BoardRepository;
import com.kh.netflix.dto.BoardDTO;
import com.kh.netflix.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private FileRepository fileRepository;


    @RequestMapping("toBoard")
    public String toBoard(Model model) throws SQLException {

        List<BoardDTO> boardList = boardRepository.selectAll();
        model.addAttribute("boardList", boardList);

        return "board/table";
    }

    @RequestMapping("toDetail")
    public String toDetail(Model model, int seq) throws SQLException {

        BoardDTO post = boardRepository.selectBySeq(seq);

        List<FileDTO> files = fileRepository.selectByBoardSeq(seq);

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "board/detail";
    }

    @RequestMapping("toPosting")
    public String toPosting() {
        return "board/posting";
    }

    @RequestMapping("posting")
    public String posting(BoardDTO board, String title, String content, MultipartFile[] files) throws SQLException, IOException {

        // boardSeq 생성
        int boardSeq = boardRepository.getNewSeq();

        board.setWriter((String) session.getAttribute("loginId"));
        board.setFreeBoard_seq(boardSeq);
        boardRepository.insert(board);

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

            FileDTO fileDTO = new FileDTO(0, orgName, sysName, boardSeq);

            fileRepository.insert(fileDTO);
        }

        return "redirect:/board/toBoard";
    }

    /**
     * <h2>File download</h2>
     */
    @RequestMapping("fileDownload")
    public void fileDownload(String sysName, String originName, HttpServletResponse response) throws IOException {

        originName = new String(originName.getBytes("utf8"), "ISO-8859-1");
                    /*
                    filePath -> target -> FileInputStream -> DataInputStream
                                    └-> fileContents  <-------------┘
                     */
        String filePath = session.getServletContext().getRealPath("upload");

        File target = new File(filePath + "/" + sysName);
//                    response.reset(); // 데이터를 보내기 전에 에러를 방지하기 위함. (필수 X)
        // 브라우저에 file이라는 것을 알려줌.(다운로드로 동작시킴.)
        // filename은 다운로드시 파일명.
        response.addHeader("content-Disposition", "attachment;filename=\"" + originName + "\"");

        byte[] fileContents = new byte[(int) target.length()]; // file을 램에 받기 위해 byte형태의 배열을 생성.
        //stream 만들기
        try (FileInputStream fis = new FileInputStream(target);
             DataInputStream dis = new DataInputStream(fis);
             ServletOutputStream sos = response.getOutputStream();
        ) {
            //배열에 데이터 담기.
            dis.readFully(fileContents);

            sos.write(fileContents);
            sos.flush();
        }
    }


    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }
}
