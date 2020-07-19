package com.sangwookim.repository;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {
    public int insert(Reply reply);
    public Reply read(Long id);
    public List<Reply> getList(Long board_id);
    public List<Reply> getListPaging(@Param("cri") Criteria cri, @Param("board_id")Long board_id);
    public int delete(Long id);
    public int update(Reply reply);
    public int getTotalReply(Long board_id);

}