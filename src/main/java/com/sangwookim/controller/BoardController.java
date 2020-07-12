package com.sangwookim.controller;

import com.sangwookim.domain.BoardVO;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.PageDTO;
import com.sangwookim.service.BoardService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {

    @Autowired
    private BoardService service;

    //    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(@RequestParam("board_info_id") Long board_info_id, Model model) {
//
//        String board_info_name = service.getBoardInfoName(board_info_id);
//        model.addAttribute("board_info_name", board_info_name);
//        model.addAttribute("board_info_id", board_info_id);
//        log.info("boardInfoName = " + board_info_name);
//
//        log.info("list");
//        model.addAttribute("list", service.getList(board_info_id));
//        return "board/list";
//    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam("board_info_id") Long board_info_id, Criteria cri, Model model) {
        cri.setStart();
        String board_info_name = service.getBoardInfoName(board_info_id);
        model.addAttribute("board_info_name", board_info_name);
        model.addAttribute("board_info_id", board_info_id);
        log.info("boardInfoName = " + board_info_name);
        log.info("cri = " + cri.getPageNum() + ", " + cri.getAmount());
        log.info("list");
        int total = service.getBoardTotal(board_info_id);
        model.addAttribute("list", service.getListPaging(cri, board_info_id));
        model.addAttribute("page", new PageDTO(cri, total));
        log.info("total = " + total);
        return "board/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(@RequestParam("board_info_id") Long board_info_id, Model model) {
        String board_info_name = service.getBoardInfoName(board_info_id);
        model.addAttribute("board_info_name", board_info_name);
        model.addAttribute("board_info_id", board_info_id);
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(BoardVO board, RedirectAttributes rttr) {
        log.info("write: " + board);
        service.write(board);
        rttr.addAttribute("board_info_id", board.getBoard_info_id());
        rttr.addFlashAttribute("result", board.getTitle());
        return "redirect:/board/list";
    }

    //    @RequestMapping(value = "/read",  method = RequestMethod.GET)
//    public String read(@RequestParam("board_info_id") Long board_info_id, @RequestParam("id") Long id, Model model) {
//        log.info("/read");
//        String board_info_name = service.getBoardInfoName(board_info_id);
//        model.addAttribute("board_info_name", board_info_name);
//        model.addAttribute("board_info_id", board_info_id);
//        model.addAttribute("board", service.read(id));
//        return "board/read";
//    }
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(BoardVO board, Criteria cri, Model model) {
        log.info("/read");
        Long board_info_id = board.getBoard_info_id();
        String board_info_name = service.getBoardInfoName(board_info_id);
        model.addAttribute("board_info_name", board_info_name);
        model.addAttribute("board", service.read(board));
        model.addAttribute("cri", cri);
        return "board/read";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(BoardVO board, Criteria cri, Model model) {
        log.info("/modify");
        Long board_info_id = board.getBoard_info_id();
        String board_info_name = service.getBoardInfoName(board_info_id);
        model.addAttribute("board_info_name", board_info_name);
        model.addAttribute("board", service.read(board));
        model.addAttribute("cri", cri);
        return "board/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(BoardVO board, Criteria cri, Model model, RedirectAttributes rttr) {
        log.info("/modify");
        service.modify(board);
        rttr.addAttribute("board_info_id", board.getBoard_info_id());
        rttr.addAttribute("id", board.getId());
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());
        return "redirect:/board/read";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("id") Long id, @RequestParam("board_info_id") Long board_info_id, Criteria cri, RedirectAttributes rttr) {
        log.info("/remove");
        service.remove(id);
        rttr.addAttribute("board_info_id", board_info_id);
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());
        return "redirect:/board/list";
    }


}
