package com.kh.netflix.controller;

import com.kh.netflix.dto.MemberDTO;
import com.kh.netflix.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class TestFree {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void selectAllTest() {

        List<MemberDTO> members = memberRepository.selectAllByMyBatis();
        for (MemberDTO member : members) {
            System.out.println(member.getId());
        }
    }
}
