package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //CRUD
    //Create
    public MemberResponse addMember(MemberRequest body) {
        Member member = memberRepository.save(new Member(body));
        return new MemberResponse(member, false);
    }

    //Read
    public List<MemberResponse> getMembers() {
        List<Member> memberList = memberRepository.findAll();
        return MemberResponse.getMembersFromEntities(memberList);
    }

    public MemberResponse getMember(String username, boolean all) {
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        return new MemberResponse(member, false);
    }

    //Update
    public MemberResponse editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        //Update stuff here
        member.setPassword((body.getPassword()));
        //TODO: Change username -> username is id - how to handle?
        // member.setUsername((body.getUsername()));
        //final Member updatedMember = memberRepository.save(member);
        return new MemberResponse(memberRepository.save(member), false);
    }

    //Delete
    public void deleteMember(String username) {
        //Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        memberRepository.delete(memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found")));
    }
}
