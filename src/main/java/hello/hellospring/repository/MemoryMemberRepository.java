package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    /*
    * 1. 실무에서는 동시성 문제가 있을 수 있으므로 ConcurrentHashMap을 사용하나, 여기서는 고려하지 않는다.
    * 2. 동일하게 동시성 문제로 long이 아닌 AtomicLong을 사용한다.
    * */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
        * stream() : loop
        * filter() : lambda 함수. member의 getName이 파라미터인 name과 같을 경우에만 필터링. 찾으면 반환!
        * findAny() : 하나라도 찾는다. 없으면 NULL 반환
         */
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
        * Repository에는 Map으로 저장되어있지만 반환은 List. 실무에서 List를 많이 사용한다.
        */
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
