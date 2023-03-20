package com.example.team1capstone.board.dto;


import com.example.team1capstone.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private int boardHits;  //게시물 조회수
    private LocalDateTime boardCreatedTime; //게시물 작성시간
    private LocalDateTime boardUpdatedTime; //게시물 수정시간
    private int price;
    private String boardSpot;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardSpot(boardEntity.getBoardSpot());
        boardDTO.setPrice(boardEntity.getPrice());
        boardDTO.setBoardCreatedTime(boardEntity.getCreateTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdateTime());
        return boardDTO;
    }

}