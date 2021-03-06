package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.CarRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //Change name of table by adding (name = "bil") to entity annotation
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CarBrand brand;
    private String model;
    private double pricePrDay;
    private double bestDiscount;
    //Liste med reservationer?

    //Administrative
    @CreationTimestamp
    private LocalDateTime created;
    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @OneToMany(mappedBy = "reservedCar", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservations(Reservation reservation) {
        reservations.add(reservation);
    }

    public Car() {}

    public Car(CarBrand brand, String model, double pricePrDay, double bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }

    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.bestDiscount = body.getBestDiscount();
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public int getId() {
        return id;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePrDay() {
        return pricePrDay;
    }

    public double getBestDiscount() {
        return bestDiscount;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPricePrDay(double pricePrDay) {
        this.pricePrDay = pricePrDay;
    }

    public void setBestDiscount(double bestDiscount) {
        this.bestDiscount = bestDiscount;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", pricePrDay=" + pricePrDay + '\'' +
                ", created=" + created +'\'' +
                ", lastEdited=" + lastEdited + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.pricePrDay, pricePrDay) == 0 && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(created, car.created) && Objects.equals(lastEdited, car.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, pricePrDay, created, lastEdited);
    }
}
