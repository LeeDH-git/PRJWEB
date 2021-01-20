package org.leedh.vo;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;


@Data
public class Criteria {

    private int pageNum; // 페이지 번호
    private int amount; // 한페이지당 개수
    private String type; // 검색 종류
    private String keyword; // 검색어

    public Criteria() {
        this(1, 10); // 기본 값을 1페이지 10개로 지정
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    // 검색 조건이 (T,W,C)로 구성되어 검색 조건을 배열로 만듬 Mybatis 동적 태그 활용
    // split은 문자열에서 나누고싶은 문자 를 기준으로 단어를 나눠 배열로 반환하여 준다.
    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }

    /* 게시물의 삭제 후에 페이지 번호나 검색 조건을 유지하기 위해
     * 'redirect'에 필요한 파라미터를 매번 추가 해야 하는 번거로움을 위한 처리
     * UriComponentsBuilder는 브라우저에서 GET방식 등의 파라미터 전송에 사용되는 문자열(query string)을 손쉽게 처리 할수 있는 클래스 */
    public String getListLink() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.getAmount())
                .queryParam("type", this.getType())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }
}
