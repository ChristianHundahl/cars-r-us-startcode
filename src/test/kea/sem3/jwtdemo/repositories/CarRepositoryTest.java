package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setup() {
        carRepository.save(new Car("Kia", "sport", 120));
        carRepository.save(new Car("Kia", "stationcar", 240));
        carRepository.save(new Car("Kia", "cross-country", 789));
    }

    @Test
    public void testCount() {
        assertEquals(3, carRepository.count());
    }

    @Test
    public void testGetByModel() {
        //assertEquals();
    }

}