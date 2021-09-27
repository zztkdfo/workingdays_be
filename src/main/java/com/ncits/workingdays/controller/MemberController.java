package com.ncits.workingdays.controller;

import com.ncits.workingdays.domain.Member;
import com.ncits.workingdays.domain.Vo.MemberVo;
import com.ncits.workingdays.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("Member 리스트")
    @RequestMapping(value = "/findMemberList", method = RequestMethod.POST)
    @ResponseBody
    public List<Member> findMemberList(){
        List<Member> list = memberService.findMemberList();
        return list;
    }

    @ApiOperation("Member 상세 정보")
    @RequestMapping(value = "/getMember", method = RequestMethod.POST)
    @ResponseBody
    public Member getMember(@RequestParam Long id){
        return memberService.getMember(id);
    }

    @ApiOperation("Member 저장")
    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    @ResponseBody
    public Member saveMember(@RequestParam MemberVo memberVo){
        return this.memberService.getMember(this.memberService.saveMember(memberVo));
    }

    @ApiOperation("Member 삭제")
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public List<Member> deleteMember(@RequestParam Long memberId){
        this.memberService.deleteMember(memberId);
        return this.memberService.findMemberList();
    }

}
