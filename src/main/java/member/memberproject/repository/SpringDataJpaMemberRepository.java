package member.memberproject.repository;

import member.memberproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    //자동으로 구현체를 만들어준다
    @Override
    Optional<Member> findByName(String name);
    //JPQL select m from Member m where m.name = ?
}
