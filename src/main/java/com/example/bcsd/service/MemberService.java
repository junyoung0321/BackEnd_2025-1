package com.example.bcsd.service;

import com.example.bcsd.Model.Member;
import com.example.bcsd.dto.MemberCreateRequest;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signUp(MemberCreateRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);
    }
}
