package com.sangwookim.controller;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Paging;
import com.sangwookim.domain.Reply;
import com.sangwookim.service.BoardService;
import com.sangwookim.slack.SlackAttachment;
import com.sangwookim.slack.SlackNotiSender;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardService service;



    @ApiOperation(value="게시글 조회", notes= "카테고리에 맞는 게시글을 불러옵니다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "category", value = "게시판 카테고리", required = true, dataType = "string", paramType = "path", defaultValue = ""),
//    })
    @ResponseBody
    @RequestMapping(value ="/list/{category}", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Board>> getList(@ApiParam(name="category", value="게시판 카테고리!", required = true)@PathVariable("category")String category, @RequestBody Criteria cri){
        log.info("category = " + category);
        return new ResponseEntity<>(service.getList(cri, category), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/paging/{category}", method = RequestMethod.POST)
    public ResponseEntity<Paging> getPaging(@RequestBody Criteria cri, @PathVariable("category") String category){
        return new ResponseEntity<>(service.getBoardPage(cri,category), HttpStatus.OK);
    } // cri의 amount와 category의 총개수를 통해 paging을 구할 수 있다.

    @ResponseBody
    @RequestMapping(value="/write",  method =  RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> write(@RequestBody Board board){
        log.info("Board =  " + board);

        return service.write(board) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Board> read(@PathVariable("id") Long id) {
        log.info("get = " + id);
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH,
            consumes = "application/json")
    public ResponseEntity<String> modify(@RequestBody Board board, @PathVariable("id") Long id) {
        board.setId(id);
        log.info("id = " + id);
        return service.modify(board) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = "application/json")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.remove(id) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
