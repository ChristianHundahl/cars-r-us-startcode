package kea.sem3.jwtdemo.entity;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reservation {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    int id;

    //Mappe en reservation til en bil hvordan?

    //Når klient reserverer bil -> modtager datatype om reservationsdato
    private LocalDate reservationDate;
    private LocalDate rentalDate;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Reservation() {}

    public Reservation(LocalDate reservationDate, LocalDate rentalDate) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

    public int getId() {
        return id;
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
                "id=" + id +
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
        return id == that.id && Objects.equals(reservationDate, that.reservationDate) && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(created, that.created) && Objects.equals(lastEdited, that.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationDate, rentalDate, created, lastEdited);
    }
}
