package org.leedh.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReplyPageVO {

    public ReplyPageVO(int replyCnt, List<ReplyVO> list) {
        this.replyCnt = replyCnt;
        this.list = list;
    }

    private int replyCnt;
    private List<ReplyVO> list;
}
