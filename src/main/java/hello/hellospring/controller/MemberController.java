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
}
