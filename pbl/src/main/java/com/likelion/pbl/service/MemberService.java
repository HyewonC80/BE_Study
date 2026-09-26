package com.likelion.pbl.service;

import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.role.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	// 생성자가 1개뿐이라 스프링이 자동으로 주입 대상임을 인식하므로 @Autowired는 생략 가능하다.
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public boolean register(Role member) {
		if (memberRepository.existsByName(member.getName())) {
			return false;
		}
		memberRepository.save(member);
		return true;
	}

	public boolean existsByName(String name) {
		return memberRepository.existsByName(name);
	}

	public Optional<Role> findByName(String name) {
		return memberRepository.findByName(name);
	}

	public List<Role> findAll() {
		return memberRepository.findAll();
	}

	public List<Role> findByPart(String part) {
		return memberRepository.findByPart(part);
	}

	public List<String> getRegisteredParts() {
		return memberRepository.getRegisteredParts();
	}
}
