package com.example.team1capstone.board.controller;

import com.example.team1capstone.board.dto.BoardDTO;
import com.example.team1capstone.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성 : 생성자 주입 방식으로 의존성 주입
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")    //saveForm() 호출이 되고 index.html을 반환
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){  //요청 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주는것을 자동화
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model){     //DB에서 전체 게시글 데이터를 가져와서 list.htmnl에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();   //BoardDTO 객체가 담겨있는 List
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")    //게시글 조회
    public String findById(@PathVariable Long id, Model model){ //URI값에 변수를 전달해서 처리하기 위해  @PathVarialble
        /* 해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 detail.html에 출력 */
        boardService.updateHits(id);    //조회수 처리
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
       BoardDTO board = boardService.update(boardDTO);
       model.addAttribute("board",board);
       return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }
}
