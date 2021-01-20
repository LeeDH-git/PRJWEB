package org.leedh.mapper;

import org.apache.ibatis.annotations.Param;
import org.leedh.vo.BoardVO;
import org.leedh.vo.Criteria;

import java.util.List;

public interface BoardMapper {

    List<BoardVO> getList();

    List<BoardVO> getListWithPaging(Criteria cri);

    void insert(BoardVO board);

    Integer insertSelectKey(BoardVO board);

    BoardVO read(Long bno);

    int delete(Long bno);

    int update(BoardVO board);

    int getTotalCount(Criteria cri);

    /* 해당 게시물의 번호인 bno아 증가나 감소를 의미하는 amount변수에 파라미터를 받을 수 있도록 처리
     * ex) 댓글이 등록되면 1이 증가 , 댓글이 감소하면 1이 감소
     * MyBatis의 SQL처리하기 위해서는 기본적으로 하나의 파라미터 타입을 사용하기 때문에
     * 2개 이상의 데이터를 전달하려면 @param 사용*/
    void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
