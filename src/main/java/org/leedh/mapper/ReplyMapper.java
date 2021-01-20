package org.leedh.mapper;

import org.apache.ibatis.annotations.Param;
import org.leedh.vo.Criteria;
import org.leedh.vo.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    /*
     * MyBatis는 두개 이상의 데이터를 파라미터로 전달하기 위해서는
     * 1. 별도의 객체로 구성
     * 2. Map을 이용
     * 3. @param을 이용해서 이름을 이용하는 방식
     * */
    int insert(ReplyVO replyVO);

    ReplyVO read(Long rno);

    int delete(Long rno);

    int update(ReplyVO replyVO);

    List<ReplyVO> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("bno") Long bno);
    // 페이징 처리를 위한 전체 댓글의 갯수 파악
    int getCountByBno(Long bno);

}
