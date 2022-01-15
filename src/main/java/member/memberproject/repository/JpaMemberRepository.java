package member.memberproject.repository;

import member.memberproject.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
//EntitiyManager로 모든게 동작된다.
//property에서 jpa라이브러리를 받으면 스프링부트가 자동으로 EntityManager를 생성
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
//***



    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);//optional로 집어넣기
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
        //inline 단축키*/
        return em.createQuery("select m from Member m", Member.class)//Member m == Member as m
                .getResultList();
    }
}
