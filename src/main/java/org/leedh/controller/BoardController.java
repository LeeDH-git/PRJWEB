package org.leedh.controller;

import lombok.extern.slf4j.Slf4j;
import org.leedh.vo.BoardVO;
import org.leedh.vo.Criteria;
import org.leedh.vo.PageVO;
import org.leedh.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * @Controller로 설정하고, '/board/'로 오는 모든 요청을 처리한다.
 *
 * #.Get방식 or Post방식 결정.
 * Get/Post방식, URI, 작동기능의 조합으로 스토리보드에 미리 URI와 Get/Post방식을 정해야 함.
 * 1. Get방식 : 외부나 다른 사람에게 메신저등으로 보낼 수 있게 하려면 반드시 Get방식 처리함.
 *              조회가 가능하도록 만들어야 하는 모든 경우 Get방식 처리해야 함.
 * 2. Post방식 : 현재 사용자가 스스로 작업하는 내용이 있는 경우 사용함.
 *              외부에 노출하는 것이 아닌 사용자 본인이 결정해서 어떤 작업을 진행하는 경우.
 *
 * #.리다이렉트 처리방식.
 * 등록/수정/삭제작업이 끝나면 어떻게 결과를 알려주고 페이지를 이동하는지에 대해서
 * 우리가 사용하는 대부분의 페이지는 Ajax 또는 REST방식으로 처리해서 멋지게 작동하지만,
 * 경험이 없을수록 목표를 단순하게 잡고 최대한 단순한 페이지구성과 많은 화면 전환을 사용해야 함.
 *
 * #.스프링 MVC 메서드의 파라미터와 리턴타입 결정시 고려사항.
 * 1. 파라미터의 수집은 스프링MVC에서 자동으로 이뤄지므로, 파라미터 수집이 필요하면
 *    원하는 객체를 파라미터로 선언한다.
 * 2. DTO클래스를 파라미터로 사용하는 것이 편리하다.
 * 3. 브라우저에서 들어오는 요청(request)이 자동으로 파라미터로 지정한 클래스 객체 속성값으로
 *    처리되는데 이를 바인딩이라고 한다.
 * 4. 스프링MVC Model객체는 해당 메서드에서 뷰(jsp...)에 필요한 데이터를 전달하는 용도로
 *    사용되므로, 만일 메서드 내에서 뷰로 전달할 데이터가 있다면, Model을 파라미터로 선언
 *    해주는 것이 편리하다.
 */

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

    //private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    /*
     * ....등록작업은 등록페이지로 이동/입력/조회(Get방식)과 데이터를 처리(Post방식)으로 구분됨.
     * .... @RequestMapping::value속성은 특정한 URI를 의미함. ...Prefix + value속성값 +
     * Suffix 형태로 이동할 페이지가 자동으로 반환됨. URL 패턴에서 기억해야 할 주요한 사실은 디폴트 접미어 패턴(default
     * suffix pattern)이 적용 된다는 점이다. 예를 들어 다음과 같이 @RequestMapping을 적용 했다고 하자.
     *
     * @RequestMapping("/hello")
     *
     * 이렇게 확장자가 붙지 않고 /로 끝나지도 않는 URL 패턴에는 디폴트 접미어 패턴이 적용돼서 실제로는 다음 세개의 URL 패턴을
     * 적용했을 때와 동일한 결과가 나온다.
     *
     * @RequestMapping("/hello" , "/hello/" , "/hello.*"})
     *
     * 따라서 /hello라고 정의한 것만으로 /hello.do , /hello.html과 같이 확장자가 붙은 URL이나 /hello/처럼
     * /로 끝나는 URL도 자동으로 매핑 된다.
     *
     * [출처] @RequestMapping 핸들러 매핑|작성자 듀시스트
     * http://blog.naver.com/PostView.nhn?blogId=deux0083&logNo=220088984575
     * .... 스프링MVC Model객체는 해당 메서드에서 뷰(jsp...)에 데이터를 전달용 객체.
     * .... Map과 유사하게 (key, value) 조합으로 데이터를 저장함.
     */
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {

        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
        // model.addAttribute("pageMaker", new PageDTO(cri, 123));

        int total = service.getTotal(cri);

        log.info("total: " + total);

        model.addAttribute("pageMaker", new PageVO(cri, total));

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("=====================================");
        log.info("register: " + board);
        log.info("=====================================");
        /*service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		*/
        return "redirect:/board/list";
    }


    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("modify : " + board);
        if (service.modify(board)) {
            rttr.addFlashAttribute("result: ", "success");
        }
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }


    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
        log.info("remove : " + bno);

        if (service.remove(bno)) {
            rttr.addFlashAttribute("result: ", "success");
        }
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }

}
