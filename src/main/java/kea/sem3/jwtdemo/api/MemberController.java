package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //TODO: Create
    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    //Read -- All user types
    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

    @GetMapping("/{username}")
    public MemberResponse getMember(@PathVariable String username) {
        return memberService.getMember(username, false);
    }

    //Update -- USER, ADMIN
    @PutMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username) {
        return memberService.editMember(body, username);
    }

    //Delete -- ADMIN
    @DeleteMapping("/{username}")
    public void deleteMember(@PathVariable String username) {
        memberService.deleteMember(username);
    }

}
