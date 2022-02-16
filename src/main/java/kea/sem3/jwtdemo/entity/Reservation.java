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
    private Car car;

    @ManyToOne
    private Member member;

    //Når klient reserverer bil -> modtager datatype om reservationsdato
    private LocalDate reservationDate;
    private LocalDate rentalDate;

    //Administrative
    @CreationTimestamp
    private LocalDateTime created;
    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Reservation() {}

    public Reservation(LocalDate reservationDate, LocalDate rentalDate) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public LocalDateTime getCreated() {
        return created;
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
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && Objects.equals(reservationDate, that.reservationDate) && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(created, that.created) && Objects.equals(lastEdited, that.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, reservationDate, rentalDate, created, lastEdited);
    }
}
