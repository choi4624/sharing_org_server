package com.example.team1capstone.board.service;

import com.example.team1capstone.board.dto.BoardDTO;
import com.example.team1capstone.board.entity.BoardEntity;
import com.example.team1capstone.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO -> Entity (Entity Class)
//Entity -> DTO (DTO Class)
//Controller로 부터 호출을 받을 때 - DTO로 받음
//Repository로 넘겨줄 때 - Entity로 넘겨줌
//DB 정보를 조회할 때 - Entity로 받아옴
//Contoller로 return 줄 때 - DTO로 바꿔서 넘겨줌

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);  //save()는 Entity 타입을 받아야 한다.

    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity)); // Entity 객체를 DTO로 변환을 하고 변환 된 객체를 List에 담는다.
        }
        return boardDTOList;
    }

    @Transactional  //수동적인 쿼리를 수행하는 경우 필요한 어노테이션
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }
        else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
