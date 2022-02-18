package kea.sem3.jwtdemo.service;

import ch.qos.logback.core.net.server.Client;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//Service-layer: converts entity classes to customer oriented objects
@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(){
        List<Car> cars = carRepository.findAll();
        return CarResponse.getCarsFromEntities(cars);
    }

    public CarResponse getCar(int id, boolean all) throws Exception {
        Car car = carRepository.findById(id).orElseThrow(()->new Client4xxException("Not found: No car with this id exists."));
        return new CarResponse(car, false);
    }
    public CarResponse addCar(CarRequest body){
        Car car = carRepository.save(new Car(body));
        return new CarResponse(car, true);
    }

    //PUT mapping
    public CarResponse editCar(CarRequest body, int id){
        //gå til car repo
        Car car = carRepository.findById(id).orElseThrow(()-> new Client4xxException("No car found for id=" + id));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setBestDiscount(body.getBestDiscount());
        car.setPricePrDay(body.getPricePrDay());
        //final Car updatedCar = carRepository.save(car);
        return new CarResponse(carRepository.save(car), true);
    }

    //PATCH mapping
    public void updateCarPrice(int id, double newPrice) {
        Car car = carRepository.findById(id).orElseThrow(()->new Client4xxException("Car could not be removed: No car with id" + id + " exists."));
        car.setPricePrDay(newPrice);
        carRepository.save(car);
    }

    public void deleteCar(int id) {
        //find car by id
        Car car = carRepository.findById(id).orElseThrow(()->new Client4xxException("Car could not be removed: No car with id" + id + " exists."));
        //Delete car
        carRepository.delete(car);
    }
}
