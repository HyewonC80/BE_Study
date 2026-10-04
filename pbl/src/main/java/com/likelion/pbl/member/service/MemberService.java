package com.likelion.pbl.member.service;

import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.domain.RoleType;
import com.likelion.pbl.member.dto.LionCreateRequest;
import com.likelion.pbl.member.dto.LionUpdateRequest;
import com.likelion.pbl.member.dto.MemberResponse;
import com.likelion.pbl.member.dto.StaffCreateRequest;
import com.likelion.pbl.member.dto.StaffUpdateRequest;
import com.likelion.pbl.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public MemberResponse createLion(LionCreateRequest request) {
		Member member = new Member(request.name(), request.major(), request.generation(), request.part(),
			RoleType.LION, request.studentId(), null);
		Member saved = memberRepository.save(member);
		return MemberResponse.from(saved);
	}

	@Transactional
	public MemberResponse createStaff(StaffCreateRequest request) {
		Member member = new Member(request.name(), request.major(), request.generation(), request.part(),
			RoleType.STAFF, null, request.position());
		Member saved = memberRepository.save(member);
		return MemberResponse.from(saved);
	}

	public Member findById(Long id) {
		return memberRepository.findById(id).orElse(null);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Transactional
	public MemberResponse updateLion(Long id, LionUpdateRequest request) {
		Member member = memberRepository.findById(id).orElse(null);
		if (member == null) {
			return null;
		}
		member.updateInfo(request.major(), request.generation(), request.part());
		member.updateStudentId(request.studentId());
		Member saved = memberRepository.save(member);
		return MemberResponse.from(saved);
	}

	@Transactional
	public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
		Member member = memberRepository.findById(id).orElse(null);
		if (member == null) {
			return null;
		}
		member.updateInfo(request.major(), request.generation(), request.part());
		member.updatePosition(request.position());
		Member saved = memberRepository.save(member);
		return MemberResponse.from(saved);
	}

	@Transactional
	public boolean deleteMember(Long id) {
		if (!memberRepository.existsById(id)) {
			return false;
		}
		memberRepository.deleteById(id);
		return true;
	}
}
