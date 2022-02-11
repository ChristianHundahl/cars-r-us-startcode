package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.MemberRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("Member")
public class Member extends BaseUser{

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private int zip;
    private boolean approved;
    private int ranking;

    //Administrative
    @CreationTimestamp
    private LocalDateTime created;
    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;


    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip, boolean approved, int ranking) {
        super (username, email, password);
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
