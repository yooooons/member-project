package member.memberproject.controller;

import member.memberproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {


    /*필드주입
          @Autowired
          private MemberService memberService;
          */

    /*setter주입
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */


    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }//생성자주입
    //생성자 주입을 일반적으로 사용한다
}
