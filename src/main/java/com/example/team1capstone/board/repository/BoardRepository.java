package com.example.team1capstone.board.repository;

import com.example.team1capstone.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> { // <Entity 클래스 이름, Entity 클래스 PK의 타입>
    //update board_table set board_hit=board_hit+1= where id=?  <- 이러한 쿼리 정의 필요.

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id") //Entity 기준으로 작성한 쿼리, b.id=Param id와 매칭
    void updateHits(@Param("id") Long id);
}
