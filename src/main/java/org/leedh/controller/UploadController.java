package org.leedh.controller;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.leedh.vo.AttachFileVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {


    /*  업로드 되는 파일을 저장 하는 방법 > transferTo(File file) : 파일의 저장
         @.MultipartFile의 메서드
            - getName() : 파라미터의 이름 <input> 태그의 이름
            - getOriginalFilename() : 업로드 되는 파일의 이름
            - (boolean type) isEmpty() : 파일이 존재 하지 않는 경우 true
            - (Long type) getSize() : 업로드 되는 파일의 크기
            - (byte[] type) getBytes() : byte[]로 파일 데이터 변환
            - (inputStream) getInputStream() : 파일 데이터와 연결된 inputStream을 반환
    */

    /*@GetMapping("/uploadAjax")
    public void uploadAjax() {
        log.info("upload Ajax");
    }*/

    /* getFolder는 오늘 날짜의 경로를 문자열로 생성
     * 생성된 경로는 폴더 경로로 수정 뒤에 반환 uploadAjaxPost에서는 해당 경로가 있는지 검사 후 폴더 생성
     * 이후에 생성된 폴더로 파일 저장 */
    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }

    /* 특정 파일의 이미지 타입 검사 */
    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /*@GetMapping("/uploadForm")
    public void uploadForm() {
        log.info("upload Form");
    }

    @PostMapping("/uploadFormAction")
    public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

        String uploadFolder = "C:\\upload";

        for (MultipartFile multipartFile : uploadFile) {

            log.info("-------------------------------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());


            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }// end catch

        }// end for
    }*/


    /* Ajax 방식으로 결과 데이터를 전달하면서 리턴타입이 달라지도록 함
     * Ajax방식을 이용하기 때문에 Model을 사용할 일이 없음
     * ResponseEntity<List<AttachFileVO>>를 반환
     * JSON 데이터를 반환 함 */
    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<AttachFileVO>> uploadAjaxPost(MultipartFile[] uploadFile) {

        List<AttachFileVO> list = new ArrayList<>();
        String uploadFolder = "C:\\upload";

        String uploadFolderPath = getFolder();
        // make folder -------------
        File uploadPath = new File(uploadFolder, uploadFolderPath);

        // 업로드 경로 존재 유무 검사
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        } // make yyyy/MM/dd folder

        for (MultipartFile multipartFile : uploadFile) {

            AttachFileVO attachFileVO = new AttachFileVO();
            String uploadFileName = multipartFile.getOriginalFilename();

            // IE has file path > IE의 경우 전체 파일 경로가 전송되므로 마지막 \\기준으로 잘라낸 문자열이 실제 파일이 됨
            if (uploadFileName != null) {

                uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
                log.info("only file name: " + uploadFileName);
                attachFileVO.setFileName(uploadFileName);

                /* 중복 방지를 위한 UUID 적용
                 * 첨부 파일은 randomUUID로 임의의 값 생성
                 * 생성된 값은 원래 파일 이름과 구분 하기 위해 중간에 "_" 추가*/
                UUID uuid = UUID.randomUUID();

                uploadFileName = uuid + "_" + uploadFileName;

                try {

                    File saveFile = new File(uploadPath, uploadFileName);
                    multipartFile.transferTo(saveFile);

                    attachFileVO.setUuid(String.valueOf(uuid));
                    attachFileVO.setUploadPath(uploadFolderPath);

                    // check image type file
                    if (checkImageType(saveFile)) {
                        attachFileVO.setImage(true);
                        FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                        Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
                        thumbnail.close();
                    }
                    // add to list
                    list.add(attachFileVO);

                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }// end catch

            }// end for
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /* getFile()은 문자열로 파일의 경로가 포함된 filName을 파라미터로 받고 byte[]를 전송
     * byte[]로 이미지 파일의 데이터를 전송할 때 브라우저별로 MIME 타입 파일의 종류에 따라 달라지는데
     * 이 부분을 해결하기 위해 probeContentType를 이용해서 적절한 MIME 타입 데이터를 Http 헤더 메시지에 표시
     * @.MIME란? https://juyoung-1008.tistory.com/4*/
    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) {

        log.info("filename: " + fileName);
        File file = new File("C:\\upload\\" + fileName);

        log.info("file: " + file);
        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    /* MIME타입은 다운로드 할 수 있는 'application/octet-stream'으로 지정하고 다운로드 시 저장되는 이름은 'Content-Disposition'을 이용해서 지정
     * 파일 이름에 대한 문자열 처리는 > 파일 이름이 한글인 경우 저장할 때 깨지는 문제를 막기 위해서
     * downloadFile()은 @RequestHeader를 이용해서 필요한 HTPP 헤더 메시지의 내용을 수집할 수 이다
     * 이를 이용해서 'User-Agent'정보를 파악하고 값이 'MSIE' 혹은 'Trident(IE 엔진)'인 경우에는 다른 방식으로 처리 */
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("user-Agent") String userAgent, String fileName) {

        Resource resource = new FileSystemResource("C:\\upload\\" + fileName);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String resourceName = resource.getFilename();

        HttpHeaders headers = new HttpHeaders();

        if (resourceName != null) {

            // resourceOriginalName을 생성하여 UUID 부분을 잘라낸 상태의 파일 이름으로 저장
            String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

            try {

                String downloadName;

                if (userAgent.contains("Trident")) {
                    log.info("IE Browser");
                    downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
                } else if (userAgent.contains("Edge")) {
                    log.info("Edge Browser");
                    downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
                } else {
                    log.info("Chrome Browser");
                    downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
                }

                log.info("downloadName: " + downloadName);

                headers.add("Content-Disposition", "attachment; filename=" + downloadName);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

    /* 서버측에서 첨부파일은 전달되는 파라미터의 이름과 종류를 파악해 처리 한다.
     * deleteFile()은 브라우저에서 전송하는 파일 이름과 종류를 파라미터로 받아서 파일의 종류에 따라 다르게 동작한다.
     * 브라우저에서 전동되는 파일 이름은 '경로 + UUID + 파일이름'으로 구성되어 있으므로, 일반 파일의 경우에는 파일만 삭제 한다.
     * 이미지의 경우 썸네일이 존재하므로, 파일 이름의 중간에 '_s'가 들어가 있다
     * 일반 이미지파일의
     *  경우'_s'가 없도록 되어 있으므로, 이 부분을 변경해서 원본 이미지 파일도 같이 삭제하도록 처리한다.*/
    @PostMapping(value = "/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type) {

        log.info("deleteFile: " + fileName);

        File file;

        try {

            file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

            file.delete();

            if (type.equals("image")) {

                String largeFileName = file.getAbsolutePath().replace("s_", "");
                log.info("largeFileName: " + largeFileName);

                file = new File(largeFileName);

                file.delete();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("deleted", HttpStatus.OK);

    }


}


