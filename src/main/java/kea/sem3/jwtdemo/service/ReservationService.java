package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //Method to create reservation and assign member, car
    //Make easier by create ReservationResponse + ReservationsRequest
    //--> method takes ReservationRequest body, gets all necessary info from there
    public ReservationResponse createReservation(ReservationRequest body, int carId, String username) {
        Reservation reservation = new Reservation(body.getRentalDate(), body.getReservedCar(), body.getMember());

        //Find needed car, member form database
        Car car = carRepository.findById(carId).orElseThrow();
        Member member = memberRepository.findById(username).orElseThrow();

        //Set needed fields in reservation
        reservation.setReservedCar(car);
        reservation.setMember(member);

        //Add reservation to car, member
        member.getReservations().add(reservation);
        car.getReservations().add(reservation);
        final Reservation newReservation = reservationRepository.save(reservation);
        return new ReservationResponse(newReservation);
    }
}
