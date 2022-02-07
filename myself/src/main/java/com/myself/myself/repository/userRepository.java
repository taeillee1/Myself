package com.myself.myself.repository;

import com.myself.myself.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {

}
