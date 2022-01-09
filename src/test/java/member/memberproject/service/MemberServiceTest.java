package member.memberproject.service;

import member.memberproject.domain.Member;
import member.memberproject.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //테스트 코드는 직관적으로 확인하기 쉽게 한글로 적기도 한다.
    MemberService memberService ;
    MemoryMemberRepository memberRepository ;

    @BeforeEach
    public void beforEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {

        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when

        memberService.join(member1);
        /* 방법1)
        try {
            memberService.join(member2);//join의 validate에서 걸린다
            fail();
        } catch (IllegalStateException e) {

            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //방법2)
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // assertThrows(NullPointerException.class, () -> memberService.join(member2)); 테스트 실패 NullPointerException


        //then
    }
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}