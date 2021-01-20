package org.leedh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leedh.vo.BoardVO;
import org.leedh.vo.Criteria;
import org.leedh.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    //private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    private BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    /* 게시물 등록 작업은 TBL_BOARD,TAL_ATTACH 테이블 모두 INSERT가 진행 되어야 하기 때문에 트랙잭션 처리 필요 */
    @Transactional
    @Override
    public void register(BoardVO board) {
        log.info("register......" + board);
        mapper.insertSelectKey(board);

    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get......" + bno);
        return mapper.read(bno);
    }

    /* 서버측 게시물 수정과 첨부파일
     * - 기존의 첨부파일 중 어떤 파일을 수정했고, 어떤 파일이 삭제되었는지 알아야 함
     * */
    @Transactional
    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......" + board);

        boolean modifyResult = mapper.update(board) == 1;

        return modifyResult;
    }

    /* 첨부파일 삭제와 실제 게시물의 삭제가 같이 처리되도록 트랜잭션 처리하에 deleteAll 호출 */
    @Transactional
    @Override
    public boolean remove(Long bno) {
        log.info("remove...." + bno);
        return mapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("get List with criteria: " + cri);
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return mapper.getTotalCount(cri);

    }
}
