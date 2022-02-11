package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;
    //TODO: CRUD for member
}
