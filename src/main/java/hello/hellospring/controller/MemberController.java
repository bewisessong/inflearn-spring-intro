package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // Spring Container에 MemberController 객체를 생성/관리한다
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        /* DI(Dependency Injection) : 의존 관계를 넣어준다 */
        this.memberService = memberService;
    }

    /* DI Setter 주입법
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    // public하게 노출되어있어서 잘못된 호출 가능성이 있다
    // memberService.setMemberRepository();   => 이걸 아무나 호출할 수 있다
    */

    /* DI Field 주입법
    @Autowired private MemberService memberService;
    // 스프링 컨테이너 말고 외부에서 변경할 수 있는 방법이 없다 
    */
}
