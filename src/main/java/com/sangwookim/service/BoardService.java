package com.sangwookim.service;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Paging;

import java.util.List;

public interface BoardService {

    public List<Board> getList(Criteria cri, String category);
//    public List<Board> getListPaging(Criteria cri, String category);
    public int write(Board board);
//    public BoardVO read(Long id);
    public Board read(Long id);
    public boolean modify(Board board);
    public boolean remove(Long id);
    public int getBoardTotal(String category);

    public Paging getBoardPage(Criteria cri, String category);


    public int getHitTotal(Long id);
    public int getHit_notTotal(Long id);

    public void updateHit(Long id);
    public void updateHit_not(Long id);


}
