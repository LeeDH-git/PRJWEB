package org.leedh.vo;

import lombok.Data;

@Data
public class AttachFileVO {

    /* @.브라우저로 전송해야 하는 데이터는 다음과 같은 정보 포함 되어야 함
     *   - 업로드된 파일의 이름과 원본 파일의 이름
     *   - 파일이 저장된 경로
     *   - 업로드된 파일이 이미지인지 아닌지에 대한 정보
     * @.정보 처리 방법
     *   1)업로드된 경로가 포함된 파일 이름을 반환하는 방식
     *   2)별도의 객체를 생성해서 처리하는 방법을 고려할 수 있다
     *   - 1은 브라우저에서 해야할 일이 많기에 2의 방식으로 구성 */
    private String fileName; // 원본 파일 이름
    private String uploadPath; // 업로드 경로
    private String uuid; // UUID값
    private boolean image; // 이미지 여부
}
