package com.sangwookim.repository;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    public List<Board> getList(String category);
    public List<Board> getListPaging(@Param("cri")Criteria cri, @Param("category")String category);
    public void insert(Board board);
    public Board read(Long id);
    public int delete(Long id);
    public int update(Board board);
    public int getBoardTotal(String category);
    public void updateReplyCount(@Param("id")Long id, @Param("amount")int amount);

    public int getHit(Long id);
    public int getHit_not(Long id);

    public void updateHit(@Param("id")Long id, @Param("amount")int amount);
    public void updateHit_not(@Param("id")Long id, @Param("amount")int amount);

}
