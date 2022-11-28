package com.kh.netflix.repository;

import com.kh.netflix.dto.FileDTO;
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
public class FileRepository {
    @Autowired
    DataSource dataSource;

    public void insert(FileDTO file) throws SQLException {
        String sql = "INSERT INTO FILES VALUES(FILE_SEQ.NEXTVAL, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, file.getOrigin_name());
            statement.setString(2, file.getSys_name());
            statement.setInt(3, file.getParent_seq());

            statement.executeUpdate();
        }
    }

    public List<FileDTO> selectByBoardSeq(int seq) throws SQLException {

        List<FileDTO> files = new ArrayList<>();

        String sql = "SELECT * FROM FILES WHERE PARENT_SEQ = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, seq);

            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()){
                    files.add(new FileDTO(resultSet));
                }
                return files;
            }
        }
    }


}
