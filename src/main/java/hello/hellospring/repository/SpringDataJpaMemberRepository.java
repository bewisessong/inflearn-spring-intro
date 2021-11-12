package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
    * JpaRepository를 확장하면 interface가 구현체를 자동으로 생성, Spring Bean에 자동으로 등록된다
    * JpaRepository<Class, PK>
    */

    @Override
    Optional<Member> findByName(String name);
    /*
    * findBy~에 대한 JPQL 작성 규칙이 있다
    * findByName(String name) => select m from Member m where m.name = ?
    * findByNameAndId(String name, Long id) => select m from Member m where m.name = ? and m.id = ?
    */
}
