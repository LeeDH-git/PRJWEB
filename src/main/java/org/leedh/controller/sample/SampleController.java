package org.leedh.controller.sample;

import org.leedh.vo.sample.SampleVO;
import org.leedh.vo.sample.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * @RestController는 JSP와 달리 순수한 데이터를 반환하는 형태로
 * 다양한 포맷의 데이터를 전송 할 수 있다
 * 주로 많이 사용하는 형태는 일반 문자열,JSON,XML
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    /*
     * 기존의 @Controller는문자열을 반환하는 경우에는 JSP 파일의 이름으로 처리하지만
     * @RestController의 경우에는 순수한 데이터가 된다
     * @GetMapping에 사용된 produces 속성은 해당 메서드가 생산하는 MINE 타입을 의미 한다
     * 예제와 같이 문자열로 직접 지정할 수도 있고 메서드내의 MediaType이라는 클래슬  이용할 수도 있다.
     * */
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        logger.info("Mine Type: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    // getSample은 XMl과 JSON 방식의 데이터를 생성할 수 있도록 작성되었다
    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample() {
        return new SampleVO(112, "스타", "로드");
    }

    // @GetMapping이나 @RequestMapping의 produces 속성은 반드시 지정해야 하는 것은 아니므로 생략도 가능
    @GetMapping(value = "/getSample2")
    public SampleVO getSample2() {
        return new SampleVO(113, "로켓", "라쿤");
    }

    /* 경우에 따라서는 여러 데이터를 한 번에 전송하기 위해서 배열이나 리스트,맵 타입의 객체를 전송하는 경우도 있다
     *
     * getList는 내부적으로 1부터 10미만 까지의 루프를 처리하면서 SampleVO객체를 만들어서 List<SampleVO>로 만들어낸다.
     * Map의 경우 '키'와 '값'을 가지는 하나의 객채로 간주된다.
     * */
    @GetMapping(value = "/getList")
    public List<SampleVO> getList() {
        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(1, i + "First", i + " Last")).collect(Collectors.toList());
    }

    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));

        return map;
    }

    /*
     * @ResponseEntity
     * REST방식으로 호출하는 경우는 화면 자체가 아니라 데이터 자체를 전송하는 방식으로 처리되기 때문에
     * 데이터를 요청한 쪽에서는 정상적인 데이터인지 비정상적인 데이터인지를 구분할 수 있는 확실한 방법을 제공해야 한다
     * ResponseEntity는 데이터와 함께 HTTP 헤더의 상태 메시지등을 같이 전달하는 용도로 사용 한다
     * HTTP 상태코드와 에러 메시지등을 함께 데이터를 전달 할 수 있기 때문에 받는 입장에서는 확실하게 결과를 알 수 있다
     * */
    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if (height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    /*
     * @RestController는 기존의 @Controller에서 사용하던 일방적인 타입이나 사용자가 정의한 타입을 사용한다
     *   @PathVariable : 일반 컨트롤러에서도 사용이 가능하지만 REST 방식에서 자주 사용된다(URL  경로 의 일부를 파라미터로 사용할 때 이용)
     *   @RequestBody : JSON 데이터를 원하는 타입의 객체로 변환해야 하는 경우에 주로 사용
     * */
    @GetMapping(value = "/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
        return new String[]{"category: " + cat, "productId: " + pid};
    }

    /*
     * @RequestBody는 전달된 요청(request)와 내용(body)을 이용해서 해당 파라미터의 타입으로 변환을 요구한다
     * 대부분의 경우에는 JSON 데이터를 서버에 보내서 원하는 타입의 객체로 변환하는 용도로 사용되지만,
     * 경우에 따라서는 원하는 포맷의 데이터를 보내고, 이를 해석해서 원하는 타입으로 사용하기도 한다
     *
     * SampleController의 다른 메서드와는 달리 PostMapping이 적용되었는데,
     * 이것은 @RequestBody가 말 그대로 요청한 내용을 처리하기 때문에 일반적인 파라미터 전달방식을 사용할 수 없기 때문이다
     * */
    @PostMapping(value = "/ticket")
    public Ticket convert(@RequestBody Ticket ticket) {
        logger.info("convert.........ticket" + ticket);
        return ticket;
    }


    /*
     * REST 방식의 데이터 교환에서 가장 튿ㄱ이한 점은 기존의 GET/POST 외에 다양한 방식으로 데이터를 전달한다는 점
     * HTTP의 전종방식은 아래와 같은 형태로 사용 된다.
     * |   직업   |  전송방식  |
     * |  Create  |  POST    |
     * |  Read    |  GET     |
     * |  Update  |  PUT     |
     * |  Delete  |  DELETE  |
     *
     * REST 방식은 URI와 같이 결합하므로 회원이라는 자원을 대상으로 전송방식을 결합하면 다음과 같은 형태가 된다.
     * |   직업   |  전송방식  |   URI
     * |  Create  |  POST    |  /members/new
     * |  Read    |  GET     |  /members/{id}
     * |  Update  |  PUT     |  /members/{id} + body(json 데이터 등)
     * |  Delete  |  DELETE  |  /members/{id}
     * */
}
