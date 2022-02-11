package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private Integer zip;
    private Boolean approved;
    private Integer ranking;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime updated;

    public MemberResponse(Member member, boolean includeAll) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();
        if(includeAll) {
            this.approved = member.isApproved();
            this.ranking = member.getRanking();
        }
    }

    public static List<MemberResponse> getMembersFromEntities(List<Member> members) {
        return members.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
    }
}
