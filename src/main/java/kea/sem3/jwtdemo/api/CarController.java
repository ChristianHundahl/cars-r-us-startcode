package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //TODO: CRUD for Car
    //Create -- ADMIN
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

    //Delete -- ADMIN
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }
}

