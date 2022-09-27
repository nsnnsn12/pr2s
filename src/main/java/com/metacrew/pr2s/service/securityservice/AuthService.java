package com.metacrew.pr2s.service.securityservice;

import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    @Autowired
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);
        Member findMember = member.orElseThrow(() -> {throw new UsernameNotFoundException(email);});
        return new User(findMember.getEmail(), findMember.getPassword(), getGrantedAuthorities(findMember));
    }

    public List<GrantedAuthority> getGrantedAuthorities(Member findMember){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(findMember.getMemberAuthority().toString()));
        return grantedAuthorities;
    }
}
