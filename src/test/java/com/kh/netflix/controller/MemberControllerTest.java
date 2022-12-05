package com.kh.netflix.controller;

import com.kh.netflix.dto.MemberDTO;
import com.kh.netflix.repository.MemberRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class MemberControllerTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(MemberControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    public void testMemberRegisterEnd() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/beginSpring/memberRegister.action")
                    .param("userid", "leess")
                    .param("passwd", "qwer1234$")
                    .param("name", "이순신")
                    .param("email","leess@gmail.com")
                    .param("tel", "01023456789")
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
            logger.info(">>>테스트 수행 성공!!<<<");
        } catch (Exception e) {
            logger.error(">>>테스트 수행 실패: "+e.getMessage());
        }
    }
}