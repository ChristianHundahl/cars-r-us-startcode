package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.CarBrand;
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
        carRepository.save(new Car(CarBrand.FORD, "sport", 120, 100));
        carRepository.save(new Car(CarBrand.SUZUKI, "stationcar", 240, 192));
        carRepository.save(new Car(CarBrand.TOYOTA, "cross-country", 789, 750));
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