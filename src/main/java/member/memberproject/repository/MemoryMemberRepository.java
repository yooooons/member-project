package member.memberproject.repository;

import member.memberproject.domain.Member;

import java.util.*;

//MemberRepository 인터페이스 구현체
public class MemoryMemberRepository implements MemberRepository{//alt+enter 메서드 오버라이드 자동추가 단축키

    private static Map<Long, Member> store = new HashMap<>();
    //    실무에서는 동시성 문제때문에 ConcurrentHashMap을 사용한다
    private static long sequence = 0L;//순서 여기서는ID를의미한다

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
//        시스템이 아이디를 정해준다
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//null일수 있을때 optional을 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
