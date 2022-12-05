package com.kh.netflix.repository;

import com.kh.netflix.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private SqlSession db;

    public List<MemberDTO> selectAllByMyBatis() {
        List<MemberDTO> members = db.selectList("Member.selectAll");
        return members;
    }

    // custom
    public List<MemberDTO> selectByCon(){
        List<MemberDTO> members = db.selectList("Member.selectAll");

        return members;
    }


    public void insert(MemberDTO memberDTO) {
        String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
        jdbc.update(sql,
                memberDTO.getId(),
                memberDTO.getPw(),
                memberDTO.getName(),
                memberDTO.getPhone(),
                memberDTO.getEmail(),
                memberDTO.getZipcode(),
                memberDTO.getAddress1(),
                memberDTO.getAddress2(),
                memberDTO.getSignup_date()
        );
    }

    public MemberDTO selectById(String id) {
        String sql = "select * from members where id=?";
        return jdbc.queryForObject(sql, new RowMapper<MemberDTO>() {
            @Override
            public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MemberDTO(rs);
            }
        }, id);
    }

    public int selectCount() {
        String sql = "select count(*) from members";
        return jdbc.queryForObject(sql, Integer.class);
    }

    public List<MemberDTO> selectAll() {
        String sql = "select * from members";
        return jdbc.query(sql, new RowMapper<MemberDTO>() {
            @Override
            public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(rs.getString("id"));
                memberDTO.setPw(rs.getString("pw"));
                memberDTO.setName(rs.getString("name"));
                memberDTO.setPhone(rs.getString("phone"));
                memberDTO.setEmail(rs.getString("email"));
                memberDTO.setZipcode(rs.getString("zipcode"));
                memberDTO.setAddress1(rs.getString("address1"));
                memberDTO.setAddress2(rs.getString("address2"));
                memberDTO.setSignup_date(rs.getTimestamp("signup_date"));
                return memberDTO;
            }
        });
    }

//
//    public void insert(MemberDTO member) throws Exception {
//        String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
//
//        try (Connection con = dataSource.getConnection();
//             PreparedStatement statement = con.prepareStatement(sql)) {
//
//            statement.setString(1, member.getId());
//            statement.setString(2, member.getPw());
//            statement.setString(3, member.getName());
//            statement.setString(4, member.getPhone());
//            statement.setString(5, member.getEmail());
//            statement.setString(6, member.getZipcode());
//            statement.setString(7, member.getAddress1());
//            statement.setString(8, member.getAddress2());
//            statement.setTimestamp(9, member.getSignup_date());
//
//            statement.executeUpdate();
//            con.commit();
//        }
//    }
//
//    public MemberDTO selectById(String id) throws Exception {
//        String sql = "select * from members where id=?";
//
//        try (Connection con = dataSource.getConnection();
//             PreparedStatement statement = con.prepareStatement(sql)) {
//
//            statement.setString(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery();) {
//
//                if (resultSet.next()) {
//                    return new MemberDTO(resultSet);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
}
