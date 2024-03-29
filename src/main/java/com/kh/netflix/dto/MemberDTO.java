package com.kh.netflix.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private Timestamp signup_date;

    public MemberDTO(String id, String pw, String name, String phone, String email, String zipcode, String address1, String address2, Timestamp signup_date) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.signup_date = signup_date;
    }

    public MemberDTO() {
    }

    public MemberDTO(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id");
        this.pw = resultSet.getString("pw");
        this.name = resultSet.getString("name");
        this.phone = resultSet.getString("phone");
        this.email = resultSet.getString("email");
        this.zipcode = resultSet.getString("zipCode");
        this.address1 = resultSet.getString("address1");
        this.address2 = resultSet.getString("address2");
        this.signup_date = resultSet.getTimestamp("signup_date");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Timestamp getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Timestamp signup_date) {
        this.signup_date = signup_date;
    }
}

