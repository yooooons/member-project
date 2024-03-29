package member.memberproject.service;

import member.memberproject.domain.Member;
import member.memberproject.repository.MemberRepository;
import member.memberproject.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//cnt shift t 테스트 단축키)테스트 자동
//@Service
@Transactional //JPA를 사용할때 필요,데이터를 저장하거나 변경할때 transactional이 있어야한다
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //MemberRepository를 외부에서 넣어준다 DI(dependency injection 의존성 주입)
    //단축키 alt insert -> construct


    /* 회원가입 */
    public Long join(Member member) {
      /*
      1)
      Optional<Member> result = memberRepository.findByName(member.getName());//cnt alt v optional로 반환자동완성 단축키
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });//요즘은 null이 있을수있을때 optional의 메서드를 사용해서 사용 if문 사용안함
        //꺼내고 싶을땐 get 사용 ex)Member member1 = result.get(); 권장x
       */
       
        /*
        2)
       memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */

            //cnt alt shift t ---extractmethod 함수를 뽑아낸다
            validateDuplicateMember(member);
            //3)중복회원 검증

            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

/*전체회원조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
/*회원아이디로조회*/
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}

