package com.example.bcsd.service;

import com.example.bcsd.Model.Member;
import com.example.bcsd.dto.MemberCreateRequest;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

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

    public String login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(member.getEmail()) // email을 식별자로 사용
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
    }
}
