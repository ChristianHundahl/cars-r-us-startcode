package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        Member m1 = new Member("test1", "test1@mail.dk", "password", "A", "A", "test123", "xxx", 1234, true, 1);
        m1.addRole(Role.USER);
        Member m2 = new Member("test2", "test2@mail.dk", "password", "B", "B", "test123", "xxx", 1234, true, 1);
        m2.addRole(Role.USER);
        Member m3 = new Member("test3", "test3@mail.dk", "password", "C", "C", "test123", "xxx", 1234, true, 1);
        m3.addRole(Role.USER);
        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);

        System.out.println("Created " + memberRepository.count() + " TEST Users");

    }

    public void makePlainCar () {
        Car car1 = new Car(CarBrand.VOLVO, "sport", 123, 100);
        Car car2 = new Car(CarBrand.TOYOTA, "pickup", 223, 109);
        Car car3 = new Car(CarBrand.VOLKSWAGEN, "stationcar", 250, 200);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

        System.out.println("Created " + carRepository.count() + " TEST Cars");
    }

    public void makePlainReservation() {
        Car car1 = new Car(CarBrand.VOLVO, "sport", 123, 100);
        Member m1 = new Member("test1", "test1@mail.dk", "password", "A", "A", "test123", "xxx", 1234, true, 1);
        memberRepository.save(m1);
        carRepository.save(car1);
        Reservation reservation1 = new Reservation(LocalDate.of(2021, 12,14), car1, m1);
        reservationRepository.save(reservation1);

        System.out.println("Created " + reservationRepository.count() + " TEST Reservations");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();

        makePlainUsers();
        makePlainCar();
        makePlainReservation();

    }
}
