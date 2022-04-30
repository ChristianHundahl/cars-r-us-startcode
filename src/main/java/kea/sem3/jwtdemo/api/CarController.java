package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin //Let's us get cars from a different URL
@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //TODO: CRUD for Car
    //Create -- ADMIN
    @RolesAllowed("ADMIN") //Permission to use this mapping only for users where role="ADMIN"
    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Read -- All user types
    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable int id) throws Exception {
        //Error handling

        return carService.getCar(id, false);
    }

    //Update -- ADMIN
    @PutMapping("/{id}")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id){
        //Find bil via id
        //Modtag data via request
        return carService.editCar(body, id);
    }

    //TODO: patch mapping
    @RolesAllowed("ADMIN")
    @PatchMapping("/{id}/{newPrice}")
    public void updateCarPrice(@PathVariable int id, @PathVariable double newPrice) {
        carService.updateCarPrice(id, newPrice);
    }

    //Delete -- ADMIN
    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }

}

