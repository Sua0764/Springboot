package dw.jdbcproject.service;

import dw.jdbcproject.model.Member;
import dw.jdbcproject.repository.JdbcMemberRepository;
import dw.jdbcproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    //강한 결합
    //@Autowired
    //JdbcMemberRepository jdbcMemberRepository;

    //약한 결합
    @Autowired
    MemberRepository memberRepository;
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
