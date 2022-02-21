package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.MemberRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("Member") //For explanation of @DiscriminatorValue, see: https://stackoverflow.com/questions/16772370/when-to-use-discriminatorvalue-annotation-in-hibernate
public class Member extends BaseUser{

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private int zip;
    @Column
    private boolean approved;
    @Column
    private int ranking;

    //Administrative
    @CreationTimestamp
    private LocalDateTime created;
    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)//One member can have many reservations
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservations(Reservation reservation) {
        reservations.add(reservation);
    };
    //TODO: test om @AllArgsConstructor kan bruges til nedarvede klasser
    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip, boolean approved, int ranking) {
        super (username, email, password);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    public Member() {}

    public Member(MemberRequest body) {
        super(body.getUsername(), body.getEmail(), body.getPassword());
        this.firstName = body.getFirstname();
        this.lastName = body.getLastname();
        this.street = body.getStreet();
        this.city = body.getCity();
        this.zip = body.getZip();
        this.ranking = body.getRanking();
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", approved=" + approved +
                ", ranking=" + ranking +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return zip == member.zip && approved == member.approved && ranking == member.ranking && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(street, member.street) && Objects.equals(city, member.city) && Objects.equals(created, member.created) && Objects.equals(lastEdited, member.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, street, city, zip, approved, ranking, created, lastEdited);
    }
}
