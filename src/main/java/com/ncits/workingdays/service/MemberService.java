package com.ncits.workingdays.service;

import com.ncits.workingdays.domain.Vo.MemberVo;
import com.ncits.workingdays.repository.MemberRepository;
import com.ncits.workingdays.domain.Member;
import com.ncits.workingdays.utils.XObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamService teamService;

    public void saveSampleMember(){
        for(int i=0; i<10; i++){
            Member member = new Member();
            member.setMemberCode("test_"+i);
            member.setMemberName("테스터_"+i);
            member.setTeam(teamService.getTeam(1L));
            memberRepository.save(member);
        }
    }

    public List<Member> findMemberList(){
        return memberRepository.findAll();
    }

    public Member getMember(Long id){

        if(memberRepository.findById(id).isPresent()){
            return memberRepository.findById(id).get();
        }

        return null;
    }

    public Long saveMember(MemberVo memberVo){
        Member member = null;
        if(XObjectUtils.isEmpty(memberVo.getId())){
            member = new Member();
            member.setMemberCode(memberVo.getMemberCode());
            member.setMemberAccount(memberVo.getMemberAccount());
        }else{
            member = this.memberRepository.findById(memberVo.getId()).get();
        }
        member.setTeam(this.teamService.getTeam(memberVo.getTeamId()));
        member.setMemberName(memberVo.getMemberName());

        return this.memberRepository.save(member).getId();

    }

    public boolean deleteMember(Long memberId){
        if(XObjectUtils.isNotEmpty(memberId)){
            if(this.memberRepository.findById(memberId).isPresent()){
                this.memberRepository.deleteById(memberId);
                return true;
            }
        }
        return false;
    }
}
