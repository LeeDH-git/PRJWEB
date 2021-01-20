package org.leedh.vo;

import lombok.Data;

@Data
public class PageVO {

    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int total;
    private Criteria cri;

    public PageVO(Criteria cri, int total) {

        this.cri = cri;
        this.total = total;
        /* - 페이징의 끝 번호 계산 > 끝 번호부터 먼저 계산하는게 수월하다. (페이지 번호는 10개씩 표시)
         *
         * Math.ceil()
         *
         * 소수점을 올림으로 처리한다.
         * 만약 전체 데이터 수가 적으면, 10페이지로 끝나면 안되는 상황이 생길 수도 있기때문에
         * 마지막 번호를 먼저 계산하는 이유는 시작 번호를 계산하기 수월하기 때문이다.
         *
         * 만일 화면에 10개씩 보여준다면 시작 번호는 무조건 끝 번호에서 9라는 값을 뺀 값이 된다.
         **/
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        // 페이징의 시작 번호 계산
        this.startPage = this.endPage - 9;

        /* - 레코드 전체 건수에 의한 마지막 페이지 재계산
           끝 번호는 전체 데이터 건수에 의해 영향을 받는다.
           예를 들어 10개씩 보여주는 경우 전체 데이터 수가 80개라고 가정하면 끝 번호는 10이 아닌 8 이 되어야만 한다.

           만약 끝 번호와 한 페이지당 출력되는 데이터 수의 곱이 전체 데이터 수보다 크면 끝 번호는 다시 total을 이용해서 다시 계산되어야 한다.

           먼저 전체 데이터 수를 이용해서 진짜 끝 페이지가 몇 번까지 되는지 계산한다.
           만약 진짜 끝 페이지가 구해놓은 끝 번호보다 작으면 끝 번호는 작은 값이 되어야만 한다.
        */
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        if (realEnd <= this.endPage) {
            this.endPage = realEnd;
        }
        // 이전 계산 : 시작 번호가 1보다 큰 경우이면 존재
        this.prev = this.startPage > 1;
        // 다음 계산 : realEnd가 끝 번호보다 큰 경우이면 존재
        this.next = this.endPage < realEnd;
    }

}

