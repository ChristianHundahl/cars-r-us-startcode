package kea.sem3.jwtdemo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Member")
public class Member extends BaseUser{

    private String firstName;
    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip, boolean approved, int ranking) {
        super (username, email, password);
        this.firstName = firstName;
    }

    public Member() {}

    public String getFirstName() {
        return firstName;
    }

    //firstName
    //lastName
    //street
    //city
    //zip
    //approved
    //ranking
}
