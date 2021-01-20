package org.leedh.vo.sample;

import lombok.Data;

@Data
public class SampleVO {

    private Integer mno;
    private String firstName;
    private String lastname;

    public SampleVO() {
    }

    public SampleVO(Integer mno, String firstName, String lastname) {
        this.mno = mno;
        this.firstName = firstName;
        this.lastname = lastname;
    }
}
