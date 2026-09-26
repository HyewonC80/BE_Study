package com.likelion.pbl.service;

import com.likelion.pbl.domain.role.Lion;
import com.likelion.pbl.domain.role.Role;
import com.likelion.pbl.domain.role.Staff;
import com.likelion.pbl.dto.LionCreateRequest;
import com.likelion.pbl.dto.LionResponse;
import com.likelion.pbl.dto.LionUpdateRequest;
import com.likelion.pbl.dto.StaffCreateRequest;
import com.likelion.pbl.dto.StaffResponse;
import com.likelion.pbl.dto.StaffUpdateRequest;
import com.likelion.pbl.repository.MemberRepository;
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

	public LionResponse createLion(LionCreateRequest request) {
		if (memberRepository.existsByName(request.name())) {
			return null;
		}
		Lion lion = new Lion(request.name(), request.major(), request.generation(), request.part(), request.studentId());
		memberRepository.save(lion);
		return LionResponse.from(lion);
	}

	public StaffResponse createStaff(StaffCreateRequest request) {
		if (memberRepository.existsByName(request.name())) {
			return null;
		}
		Staff staff = new Staff(request.name(), request.major(), request.generation(), request.part(), request.position());
		memberRepository.save(staff);
		return StaffResponse.from(staff);
	}

	public LionResponse updateLion(String name, LionUpdateRequest request) {
		if (!memberRepository.existsByName(name)) {
			return null;
		}
		Lion updated = new Lion(name, request.major(), request.generation(), request.part(), request.studentId());
		memberRepository.updateByName(name, updated);
		return LionResponse.from(updated);
	}

	public StaffResponse updateStaff(String name, StaffUpdateRequest request) {
		if (!memberRepository.existsByName(name)) {
			return null;
		}
		Staff updated = new Staff(name, request.major(), request.generation(), request.part(), request.position());
		memberRepository.updateByName(name, updated);
		return StaffResponse.from(updated);
	}

	public boolean deleteMember(String name) {
		return memberRepository.deleteByName(name);
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
