package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        /*Reservation r1 = new Reservation(LocalDate.of(2021, 12,14));
        Reservation r2 = new Reservation(LocalDate.of(2021, 12,14));
        Reservation r3 = new Reservation(LocalDate.of(2021, 12,14));
        reservationRepository.save(r1);
        reservationRepository.save(r2);
        reservationRepository.save(r3);*/

    }

    @Test
    public void testCount() {
        assertEquals(3, reservationRepository.count());
    }

    @Test
    public void findByReservationId() {
        Reservation found = reservationRepository.getById(1);
        assertNotEquals(0, found.getReservationId());
    }

}