package com.myself.myself.repository;

import com.myself.myself.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface boardRepository extends JpaRepository<Board,Long> {

}
