package com.sangwookim.repository;

import com.sangwookim.domain.BoardVO;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {
    public int insert(ReplyVO reply);
    public ReplyVO read(Long id);
    public List<ReplyVO> getList(Long board_id);
    public List<ReplyVO> getListPaging(@Param("cri") Criteria cri, @Param("board_id")Long board_id);
    public int delete(Long id);
    public int update(ReplyVO reply);
    public int getTotalReply(Long board_id);

}