package org.leedh.service;

import org.leedh.vo.Criteria;
import org.leedh.vo.ReplyPageVO;
import org.leedh.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    int register(ReplyVO replyVO);

    ReplyVO get(Long rno);

    int modify(ReplyVO replyVO);

    int remove(Long rno);

    List<ReplyVO> getList(Criteria cri, Long bno);

    ReplyPageVO getListPage(Criteria cri, Long bno);
}
