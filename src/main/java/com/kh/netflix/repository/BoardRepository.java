package com.kh.netflix.repository;

import com.kh.netflix.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {
    @Autowired
    DataSource dataSource;

    public BoardDTO selectBySeq(int seq) throws SQLException {
        String sql = "SELECT * FROM FREEBOARD WHERE FREEBOARD_SEQ = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, seq);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BoardDTO(resultSet);
                }
                return new BoardDTO();
            }
        }
    }

    public List<BoardDTO> selectAll() throws SQLException {
        String sql = "SELECT * FROM FREEBOARD ORDER BY 1";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();) {

            List<BoardDTO> boardList = new ArrayList<>();

            while (rs.next()) {
                boardList.add(new BoardDTO(rs));
            }

            return boardList;
        }
    }

    public void insert(BoardDTO board) throws SQLException {
        String sql = "INSERT INTO FREEBOARD VALUES(?, ?, ?, ?, SYSDATE, 0)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, board.getFreeBoard_seq());
            statement.setString(2, board.getWriter());
            statement.setString(3, board.getTitle());
            statement.setString(4, board.getContent());

            statement.executeUpdate();
        }
    }

    public int getNewSeq() throws SQLException {
        Optional<Integer> seq = Optional.empty();

        String sql = "SELECT FREEBOARD_SEQ.NEXTVAL AS SEQ FROM DUAL";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();) {

            if(resultSet.next()){
                seq = Optional.of(resultSet.getInt("seq"));
            }
            return seq.orElse(0);
        }

    }
}
