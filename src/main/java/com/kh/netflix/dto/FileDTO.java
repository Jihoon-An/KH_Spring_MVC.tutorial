package com.kh.netflix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private int file_seq;
    private String origin_name;
    private String sys_name;
    private int parent_seq;

    public FileDTO(ResultSet resultSet) throws SQLException {
        this.file_seq = resultSet.getInt("FILE_SEQ");
        this.origin_name = resultSet.getString("ORIGIN_NAME");
        this.sys_name = resultSet.getString("SYS_NAME");
        this.parent_seq = resultSet.getInt("PARENT_SEQ");
    }
}
