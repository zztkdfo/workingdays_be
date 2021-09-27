package com.ncits.workingdays.controller;

import com.ncits.workingdays.domain.Member;
import com.ncits.workingdays.domain.Vo.MemberVo;
import com.ncits.workingdays.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping
    @ResponseBody
    public String Test(){
        return "Test Member";
    }

    @RequestMapping(value = "/saveSampleMember")
    @ResponseBody
    public List<Member> saveSampleMember(){
        memberService.saveSampleMember();
        List<Member> list = memberService.findMemberList();
        return list;
    }

    @RequestMapping(value = "/findMemberList")
    @ResponseBody
    public List<Member> findMemberList(){
        List<Member> list = memberService.findMemberList();
        return list;
    }

    @RequestMapping(value = "/getMember", method = RequestMethod.POST)
    @ResponseBody
    public Member getMember(@RequestParam Long id){
        return memberService.getMember(id);
    }

    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    @ResponseBody
    public Member saveMember(@RequestParam MemberVo memberVo){
        return this.memberService.getMember(this.memberService.saveMember(memberVo));
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public List<Member> deleteMember(@RequestParam Long memberId){
        this.memberService.deleteMember(memberId);
        return this.memberService.findMemberList();
    }

}
