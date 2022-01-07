package member.memberproject.repository;

import member.memberproject.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void  afterEach(){
        repository.clearStore();
    }
    //테스트 하나가 끝나면 데이터를 클리어하는 메서드
    //테스트가 끝날때 마다 repository를 지워줌

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result="+(result==member));
//        Assertions.assertEquals(member,result);
//        Assertions.assertThat(member).isEqualTo(result);//Assertions를 alt + enter로 static import 로 만들수있다
        assertThat(member).isEqualTo(result);//static import로 하면 이렇게 변경
    }

    @Test
    public void findByName() {
        Member member1 = new Member();//shift F6 rename 단축키
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        //get()함수 를 사용하면 optional을 까서 꺼낼수있다 즉,Optional<Member> result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findByAll() {
        Member member1 = new Member();//shift F6 rename 단축키
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
