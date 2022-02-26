package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.entity.CarBrand;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*Denne gør at vi kan få en fuld applications test
Når Lars siger byggeservere, refererer han til githubAction
Denne annotation kører alle annotationer*/
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
/*referer til @Profile("!test") (som ligger i MakeTestData), som altså fortæller at vi ønsker at få
data fra denne klasse med over i denne test */
@ActiveProfiles("test")
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String id1, id2;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
        id1 = memberRepository.save(new Member("username1", "email1@mail.dk", "String password", "String firstName", "String lastName", "String street", "String city", 1234, true, 1)).getUsername();
        id2 = memberRepository.save(new Member("username2", "email2@mail.dk", "String password2", "String firstName2", "String lastName2", "String street2", "String city2", 1234, true, 1)).getUsername();
    }

    @Test
    void getMembers() {
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/members/" + id1))
                .andExpect(status().isOk());
        System.out.println(memberRepository.count());
        assertEquals(1, memberRepository.count());
    }

    @Test
    void addMember() throws Exception {
        MemberRequest newMember = new MemberRequest(Role.USER, "username3", "email3@mail.dk", true, "password3", 1, "Test", "Tester", "Test street", "Testtown", 1111);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .contentType("application/json")
                        .accept("application/json")//String showing accepted type -- see CarController
                        .content(objectMapper.writeValueAsString(newMember)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists());
        //Verify that it actually ended in the database
        assertEquals(3, memberRepository.count());
    }
}