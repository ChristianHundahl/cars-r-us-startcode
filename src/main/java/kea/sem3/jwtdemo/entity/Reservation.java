package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reservation {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private int reservationId;

    //TODO: Car klassen addReservation
    //TODO: Member klassen addReservation
    @ManyToOne
    private Car reservedCar;

    @ManyToOne
    private Member member;

    //Administrative

    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    //Når klient reserverer bil -> modtager datatype om reservationsdato
    @CreationTimestamp
    private LocalDate reservationDate;
    private LocalDate rentalDate;

    public Reservation() {}

    public Reservation(LocalDate rentalDate, Car reservedCar, Member member) {
        this.rentalDate = rentalDate;
        this.reservedCar = reservedCar;
        this.member = member;
        reservedCar.addReservations(this);
        member.addReservations(this);
    }

    public Car getReservedCar() {
        return reservedCar;
    }

    public void setReservedCar(Car car) {
        this.reservedCar = car;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }



    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + reservationId +
                ", reservationDate=" + reservationDate +
                ", rentalDate=" + rentalDate +
                ", lastEdited=" + lastEdited +
                '}';
    }

   }


