package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // Spring Container에 MemberController 객체를 생성/관리한다
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        /* DI(Dependency Injection) : 의존 관계를 넣어준다 */
        this.memberService = memberService;
        //System.out.println("memberService = " + memberService.getClass()); // AOP 사용 시 MembersService$$EnhancerBySpringCGLIB 으로 나온다. Proxy MemberService !
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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String crate(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
