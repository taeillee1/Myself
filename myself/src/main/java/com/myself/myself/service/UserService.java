package com.myself.myself.service;

import com.myself.myself.entity.User;
import com.myself.myself.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private userRepository userRepository;

    @Transactional
    public void signup(User user){
        userRepository.save(user);
    }
}
