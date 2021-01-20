package org.leedh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leedh.vo.Criteria;
import org.leedh.vo.ReplyPageVO;
import org.leedh.vo.ReplyVO;
import org.leedh.mapper.BoardMapper;
import org.leedh.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    //private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

    private ReplyMapper mapper;
    private BoardMapper boardMapper;


    public ReplyServiceImpl(ReplyMapper mapper, BoardMapper boardMapper) {
        this.mapper = mapper;
        this.boardMapper = boardMapper;
    }


    /* 댓글 등록의 경우 파라미터로 전달받은 ReplyVo 내에 게시물 번호가 존재하므로 이를 이용해서 댓글을 추가 */
    @Transactional
    @Override
    public int register(ReplyVO replyVO) {
        log.info("register......" + replyVO);
        boardMapper.updateReplyCnt(replyVO.getBno(), 1);
        return mapper.insert(replyVO);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("get.......");
        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO replyVO) {
        log.info("modify........");
        return mapper.update(replyVO);
    }

    /* 댓글 삭제는 전달되는 파라미터가 댓글의 번호인 rno만을 받기 때문에 해당 댓글의 게시물을 알아가는 과정 필요 */
    @Transactional
    @Override
    public int remove(Long rno) {
        log.info("remove........" + rno);
        ReplyVO replyVO = mapper.read(rno);
        boardMapper.updateReplyCnt(replyVO.getBno(), -1);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("get Reply Lis of a Board " + bno);
        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageVO getListPage(Criteria cri, Long bno) {
        return new ReplyPageVO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri, bno)
        );
    }
}
