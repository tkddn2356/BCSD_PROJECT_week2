package com.sangwookim.service;

import com.sangwookim.domain.BoardVO;
import com.sangwookim.domain.Criteria;

import java.util.List;

public interface BoardService {

    public List<BoardVO> getList(Long board_info_id);
    public List<BoardVO> getListPaging(Criteria cri, Long board_info_id);
    public void write(BoardVO board);
//    public BoardVO read(Long id);
    public BoardVO read(BoardVO board);
    public String getBoardInfoName(Long board_info_idx);
    public boolean modify(BoardVO board);
    public boolean remove(Long id);
    public int getBoardTotal(Long board_info_id);


}
