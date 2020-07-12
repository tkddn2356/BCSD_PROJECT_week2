package com.sangwookim.repository;

import com.sangwookim.domain.BoardVO;
import com.sangwookim.domain.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    public List<BoardVO> getList(Long board_info_id);
    public List<BoardVO> getListPaging(@Param("cri")Criteria cri, @Param("board_info_id")Long board_info_id);
    public void insert(BoardVO board);
//    public void insertSelectKey(BoardVO board);
//    public BoardVO read(Long id);
    public BoardVO read(BoardVO board);
    public String getBoardInfoName(Long board_info_id);
    public int delete(Long id);
    public int update(BoardVO board);
    public int getBoardTotal(Long board_info_id);

}
