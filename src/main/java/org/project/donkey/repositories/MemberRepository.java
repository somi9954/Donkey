package org.project.donkey.repositories;

import org.project.donkey.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);

    default boolean exists(String email) {
        return exists(QMember.member.email.eq(email));
    }
}
