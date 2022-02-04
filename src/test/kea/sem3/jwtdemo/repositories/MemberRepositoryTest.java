package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member m1 = new Member("m1", "m1@mail.dk", "test123", "xxx", "yyy",
                "Algade", "Roskilde", 4000, true, 2);
        memberRepository.save(m1);

        Member m2 = new Member("m2", "m2@mail.dk", "test123", "xxx", "yyy",
                "Algade", "Roskilde", 4000, true, 2);
        memberRepository.save(m2);

    }

    @Test
    public void testCount() {
        assertEquals(2, memberRepository.count());
    }

    @Test
    public void findByUsername() {
        Member found = memberRepository.getById("m1");
        assertEquals("m1", found.getUsername());
    }

    @Test
    public void addMember() {
        Member added = memberRepository.save(new Member("m3", "m3@mail.dk", "test123", "xxx", "yyy",
                "Algade", "Roskilde", 4000, true, 2));
        assertNotEquals(null, added.getUsername());
    }
}