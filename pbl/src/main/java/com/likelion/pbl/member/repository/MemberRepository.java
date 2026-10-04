package com.likelion.pbl.member.repository;

import com.likelion.pbl.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByName(String name);
}
