package com.kh.netflix.controller;

import com.kh.netflix.dto.MemberDTO;
import com.kh.netflix.repository.MemberRepository;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import static org.hamcrest.MatcherAssert.assertThat;

// spring container test 설정
@RunWith(SpringJUnit4ClassRunner.class)
// spring context 지정
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
// 테스트 하는 과정에 web통신이 필요할 경우 작성
@WebAppConfiguration  
public class HomeControllerTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(HomeControllerTest.class);
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private WebApplicationContext wac; // 테스트 코드 작성에 사용될 Spring container instance di

    // Annotation 설정에 의해 만들어진 가상 Tomcat + String 환경에 request / response 작업을 수행할 수 있는 기능을 포함.
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCodeTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/testCode")) // get으로 수행
                .andDo(MockMvcResultHandlers.print()) // 결과 출력
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()); // 결과 예측

    }

    @Test
    public void insertTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/insert")
                        .param("writer", "Tome") // parameter값 입력.
                        .param("message", "JUinit practice")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void myPageTest() {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginId", "abg1418");

        MemberDTO member = memberRepository.selectById("abg1418");

        MemberDTO memberTestSet = new MemberDTO();
        memberTestSet.setPhone(member.getPhone());
        memberTestSet.setName(member.getName());
        memberTestSet.setEmail(member.getEmail());
        memberTestSet.setZipcode(member.getZipcode());
        memberTestSet.setPw(member.getPw());
        memberTestSet.setAddress1(member.getAddress1());
        memberTestSet.setAddress2(member.getAddress2());
        memberTestSet.setSignup_date(member.getSignup_date());
        memberTestSet.setId(member.getId());

        try {
            System.out.println(member.toString());
            System.out.println(memberTestSet.toString());

            Assertions.assertThat(memberTestSet)
                    .usingRecursiveComparison()
                    .isEqualTo(member);

//            this.mockMvc.perform(MockMvcRequestBuilders.post("/member/toMypage")
//                            .session(session)
//                    )
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            logger.info("test fail");
        }

    }
}