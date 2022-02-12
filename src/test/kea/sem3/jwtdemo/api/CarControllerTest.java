package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.CarBrand;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


/*Denne gør at vi kan få en fuld applications test
Når Lars siger byggeservere, refererer han til githubAction
Denne annotation kører alle annotationer*/
@SpringBootTest

@AutoConfigureMockMvc
/*referer til @Profile("!test") (som ligger i MakeTestData), som altså fortæller at vi ønsker at få
data fra denne klasse med over i denne test */
@ActiveProfiles("test")
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    static int carFordId, carSuzukiId;

    @BeforeEach
    public void setup() {
        carRepository.deleteAll();
        carFordId = carRepository.save(new Car(CarBrand.FORD, "Focus", 700, 20)).getId();
        carSuzukiId = carRepository.save(new Car(CarBrand.SUZUKI, "Vitara", 500, 14)).getId();
    }

    @Test //Passed
    void getCars() {
    }

    @Test //Passed
    public void testCarById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        /*De to linjer, er requestet*/
                        .get("/api/cars/" + carFordId)
                        .accept(MediaType.APPLICATION_JSON))

                /*.andDo(print()): denne metode bruges til at printe ens fejl for lettere at identificere fejlen,
                men hvis alt kører er den overflødig. Den printer returværdierne, som er blevet omformet til json*/
                /*Her sker response*/
                .andExpect(status().isOk())
                /*jsonPath, parser respons som json*/
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(carFordId))

                /*Den kigger på det json den fik retur og tjek om der findes "Focus" i json*/
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Focus"));
    }

    @Test //Passed
    public void testAllCars() throws Exception {
        String model = "$[?(@.model == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                //One way of testing this using regex
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Focus").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Vitara").exists())
                //Another way of testing using containsString
                .andExpect(MockMvcResultMatchers.content().string(containsString("Focus")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Vitara")));
    }

    @Test //Passed
    public void testAddCar() throws Exception {
        CarRequest newCar = new CarRequest(CarBrand.VOLKSWAGEN, "Polo", 200, 10);
        System.out.println("xxxxxx"+objectMapper.writeValueAsString(newCar));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cars")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(newCar)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        //Verify that it actually ended in the database
        assertEquals(3, carRepository.count());

    }

    @Test //Passed
    public void editCar() throws Exception {
        //New price and discount for the ford
        CarRequest carToEdit = new CarRequest(CarBrand.FORD, "Focus", 500, 20);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cars/" + carFordId)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(carToEdit)))
                .andExpect(status().isOk());
        Car editedCarFromDB = carRepository.findById(carFordId).orElse(null);
        assertEquals(500, editedCarFromDB.getPricePrDay());
        assertEquals(20, editedCarFromDB.getBestDiscount());
    }

    @Test //Passed
    void deleteCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cars/" + carFordId))
                .andExpect(status().isOk());
        //Verify that we only have one car in the database
        assertEquals(1, carRepository.count());
    }

}