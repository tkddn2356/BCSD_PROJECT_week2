package com.sangwookim.controller;

import com.sangwookim.domain.Criteria;
import com.sangwookim.service.BoardService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardViewController {

    @Autowired
    private BoardService service;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam("category") String category, Criteria cri, Model model) {
        //cri의 기본생성자 실행됨
        model.addAttribute("category", category);
        model.addAttribute("criteria", cri);
        return "board/list";
    }

//    @RequestMapping(value = "/write", method = RequestMethod.GET)
//    public String write(@RequestParam("category") String category, Model model) {
//        model.addAttribute("category", category);
//        return "board/write";
//    }
//
//    @RequestMapping(value = "/write", method = RequestMethod.POST)
//    public String write_pro(Board board, RedirectAttributes rttr) {
//        log.info("write: " + board);
//        service.write(board);
//        rttr.addAttribute("category", board.getCategory());
//        return "redirect:/board/list";
//    }
//
////    @RequestMapping(value = "/read", method = RequestMethod.GET)
////    public String read(@RequestParam("id") Long id, Model model) {
////        model.addAttribute("board", service.read(id));
////        return "board/read";
////    }
//
//    @RequestMapping(value = "/read", method = RequestMethod.GET)
//    public String read(@RequestParam("category")String category, @RequestParam("id") Long id, Criteria cri, Model model) {
//        log.info("/read");
//        model.addAttribute("category", category);
//        model.addAttribute("board", service.read(id));
//        model.addAttribute("cri", cri);
//        return "board/read";
//    }
//
//    @RequestMapping(value = "/modify", method = RequestMethod.GET)
//    public String modify(@RequestParam("category")String category, @RequestParam("id") Long id, Criteria cri, Model model) {
//        log.info("/modify");
//        model.addAttribute("category", category);
//        model.addAttribute("board", service.read(id));
//        model.addAttribute("cri", cri);
//        return "board/modify";
//    }
//
//    @RequestMapping(value = "/modify", method = RequestMethod.POST)
//    public String modify(Board board, Criteria cri, RedirectAttributes rttr) {
//        log.info("/modify");
//        service.modify(board);
//        rttr.addAttribute("category", board.getCategory());
//        rttr.addAttribute("id", board.getId());
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//        return "redirect:/board/read";
//    }
//
//    @RequestMapping(value = "/remove", method = RequestMethod.POST)
//    public String remove(@RequestParam("category") String category, @RequestParam("id") Long id, Criteria cri, RedirectAttributes rttr) {
//        log.info("/remove");
//        service.remove(id);
//        rttr.addAttribute("category", category);
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//        return "redirect:/board/list";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/hit/{id}", method = RequestMethod.PATCH,
//            consumes = "application/json")
//    public ResponseEntity<String> updateHit(@PathVariable("id")Long id){
//        service.updateHit(id);
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/hit_not/{id}", method = RequestMethod.PATCH,
//            consumes = "application/json")
//    public ResponseEntity<String> updateHit_not(@PathVariable("id")Long id){
//        service.updateHit_not(id);
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/hit/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Integer> getHit(@PathVariable("id")Long id){
//        return new ResponseEntity<>(service.getHitTotal(id), HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/hit_not/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Integer> getHit_not(@PathVariable("id")Long id){
//        return new ResponseEntity<>(service.getHit_notTotal(id), HttpStatus.OK);
//    }


}
