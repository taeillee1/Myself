package com.myself.myself.service;

import com.myself.myself.entity.User;
import com.myself.myself.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final userRepository userRepository;

    @Transactional
    public User signup(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user){
        User findMember = userRepository.findByUsername(user.getUsername());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //데이터베이스에서 회원정보를 가져오는 역할을 담당한다.

        User user = userRepository.findByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

}
