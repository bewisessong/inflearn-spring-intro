package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // 단순히 출력해서 확인하는 방법은 아래와 같다
        // System.out.println("result = " + (result == member));

        /*
        * org.jnuit.jupiter.api.Assertions
        * assertEquals(expected, actual) : actual이 expected와 같은지 비교한다
        * 성공 시 체크표시, 실패 시 에러 발생
        */
        // Assertions.assertEquals(member, result);

        /*
        * org.assertj.core.api
        * assertThat(expected).isEqualTo(actual) : expected가 actual과 동일한지 비교한다
        * Assertions 옵션 창에서 static import
        */
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        /*
        * 복사하고 이름만 바꿀 때는 이름에 커서 두고 Shift + F6
        */
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
