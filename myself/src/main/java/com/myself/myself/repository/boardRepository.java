package com.myself.myself.repository;

import com.myself.myself.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface boardRepository extends JpaRepository<Board,Long>{
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByContentContaining(String content, Pageable pageable);
}
