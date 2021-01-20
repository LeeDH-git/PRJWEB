//package org.leedh.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.leedh.domain.BoardVO;
//import org.leedh.domain.Criteria;
//import org.leedh.domain.ReplyPageVO;
//import org.leedh.domain.ReplyVO;
//import org.leedh.service.ReplyService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.RequestPath;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/replies/*")
//public class LoginController {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private ReplyService service;
//
//    @Autowired
//    public LoginController(ReplyService service) {
//        this.service = service;
//    }
//
//
//    /*
//     * 등록 : URL -> /replies/new 			   | HTTP 전송박식 -> POST
//     * 조회 : URL -> /replies/:rno 			   | HTTP 전송박식 -> GET
//     * 삭제 : URL -> /replies/:rno 			   | HTTP 전송박식 -> DELETE
//     * 수정 : URL -> /replies/:rno 			   | HTTP 전송박식 -> PUT/PATCH
//     * 페이지: URL -> /replies/pages/:bno/:page | HTTP 전송박식 -> GET
//     *
//     * REST 방식으로 동작하는 URL을 설계할 때는 PK를 기준으로 작성 > PK만으로 조회,수정,삭제가 가능하기에
//     * (댓글 목록은 PK를 사용할 수 없기 때문에 파라미터로 필요한 게시물의 ㅂ번호와 페이지번호 정보들로 URL에서 표시
//     *
//     * */
//
//
//    /* 댓글 등록 : 브라우저 -> JSON 타입으로 댓글 데이터 전송
//     *            서버에서 -> 정상처리 되있는지 문자열로 결과 전송
//     * consumes : JSON방식의 데이터만 처리 , produces : 문자열을 반환하도록 처리
//     * @RequestBody : JSON데이터를 VO로 변환하도록 지정
//     **/
//    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
//    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) {
//
//        log.info("ReplyVO: " + replyVO);
//        int insertCount = service.register(replyVO);
//        log.info("Reply insert COUNT: " + insertCount);
//
//        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        // 삼항 연산자 처리
//    }
//
//    /**
//     * getList는 Criteria를 이용하여 파라미터 수집
//     * /{bno}/{page}의 'page'값은 Criteria를 생성하여 직접 처리
//     * JSON 데이터 전송 , ReplyPageVO객체를 JSON으로 전송하게 됨
//     * 특정 게시물의 댓글 목록을 조회하면 'replyCnt'와 'list'라는 이름의 속성을 가진 JSON 문자열 전송
//     * 게시물의 번호는 @PathVariable을 이용해서 파라미터로 처리하고 브라우저에서 아래와 같이 테스트
//     * http://localhost:포트번호/replies/pages/3145745/1
//     */
//    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<ReplyPageVO> getList(@PathVariable("page") int page,
//                                               @PathVariable("bno") Long bno) {
//        Criteria cri = new Criteria(page,10);
//        log.info("get Reply List bno: " + bno);
//        log.info("cri: " + cri);
//
//        return new ResponseEntity<>(service.getListPage(cri,bno),HttpStatus.OK);
//
//    }
//    /*
//     * RestController에서 댓글 수정/삭제/조회는 JSON이나 문자열을 반환하도록 설계 */
//    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
//
//        log.info("get: " + rno);
//
//        return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
//    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
//
//        log.info("remove : " + rno);
//
//        return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /*
//     * 댓글 수정은 JSON형태로 전달되는 데이터 / 파라미터로 전달되는 댓글 번호(rno)를 처리하는데
//     * 이에 대한 처리는 'PUT'방식이나 'PATCH' 방식을 이용하도록 처리
//     * @RequestBody로 처리되는 데이터는 일반 파라미터나 @PathVariable 파라미터로 처리할 수 없기 때문에 직첩 처리해주는 부분을 주의 해야 함 */
//    @RequestMapping(value = "{rno}", method = {RequestMethod.GET, RequestMethod.PATCH}, consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
//    public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO, @PathVariable("rno") Long rno) {
//
//        replyVO.setRno(rno);
//        log.info("rno: " + rno);
//        log.info("modify: " + replyVO);
//
//        return service.modify(replyVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//
//
//
//
//
//}
