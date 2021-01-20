package org.leedh.service;

import java.util.List;

import org.leedh.vo.BoardVO;
import org.leedh.vo.Criteria;

public interface BoardService {

    void register(BoardVO board);

    BoardVO get(Long bno);

    boolean modify(BoardVO board);

    boolean remove(Long bno);

    // public List<BoardVO> getList();

    List<BoardVO> getList(Criteria cri);

    //추가
    int getTotal(Criteria cri);

}
