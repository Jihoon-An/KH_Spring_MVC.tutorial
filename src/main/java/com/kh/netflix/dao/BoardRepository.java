package com.kh.netflix.dao;

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
        String sql = "INSERT INTO FREEBOARD VALUES(FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, board.getWriter());
            statement.setString(2, board.getTitle());
            statement.setString(3, board.getContent());

            statement.executeUpdate();
        }
    }
}
