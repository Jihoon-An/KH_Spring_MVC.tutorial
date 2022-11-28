package com.kh.netflix.dao;

import com.kh.netflix.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class MemberRepository {
    @Autowired
    DataSource dataSource;

    public void insert(MemberDTO member) throws Exception {
        String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, member.getId());
            statement.setString(2, member.getPw());
            statement.setString(3, member.getName());
            statement.setString(4, member.getPhone());
            statement.setString(5, member.getEmail());
            statement.setString(6, member.getZipCode());
            statement.setString(7, member.getAddress1());
            statement.setString(8, member.getAddress2());
            statement.setTimestamp(9, member.getSignup_date());

            statement.executeUpdate();
            con.commit();
        }
    }

    public MemberDTO selectById(String id) throws Exception {
        String sql = "select * from members where id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery();) {

                if (resultSet.next()) {
                    return new MemberDTO(resultSet);
                } else {
                    return null;
                }
            }
        }
    }
}
