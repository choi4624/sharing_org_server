package com.example.team1capstone.board.entity;

import com.example.team1capstone.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{  //DB 테이블 역할을 하는 클래스
    @Id //pk 컬럼 지정(필수)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment : 데이터 추가 시 1씩 증가
    private Long id;

    @Column(length = 20, nullable = false)  //크기 20, not null
    private String boardWriter;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private String boardSpot;

    @Column
    private int price;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){  //DTO에 담긴 값들을 Entity 객체로 옮겨 담는다. Id가 없기 떄문에 jpa는 insert라고 인지
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardSpot(boardDTO.getBoardSpot()); //updated spots, price
        boardEntity.setPrice(boardDTO.getPrice());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());    //Id가 있어야만 save에서 update쿼리를 전달 할 수 있다. Id가 존재하기 떄문에 jpa는 update라고 인지
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardSpot(boardDTO.getBoardSpot()); //updated spots, price
        boardEntity.setPrice(boardDTO.getPrice());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }


}
