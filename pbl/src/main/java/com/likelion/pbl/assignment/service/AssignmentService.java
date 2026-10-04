package com.likelion.pbl.assignment.service;

import com.likelion.pbl.assignment.domain.Assignment;
import com.likelion.pbl.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl.assignment.dto.AssignmentResponse;
import com.likelion.pbl.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl.assignment.repository.AssignmentRepository;
import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

	private final AssignmentRepository assignmentRepository;
	private final MemberRepository memberRepository;

	public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
		this.assignmentRepository = assignmentRepository;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public AssignmentResponse createAssignment(Long memberId, AssignmentCreateRequest request) {
		Member member = memberRepository.findById(memberId).orElse(null);
		if (member == null) {
			return null;
		}
		Assignment assignment = new Assignment(request.title(), request.description(), member);
		Assignment saved = assignmentRepository.save(assignment);
		return AssignmentResponse.from(saved);
	}

	public List<AssignmentResponse> findByMemberId(Long memberId) {
		return assignmentRepository.findByMemberId(memberId).stream()
			.map(AssignmentResponse::from)
			.toList();
	}

	public AssignmentResponse findById(Long id) {
		Assignment assignment = assignmentRepository.findById(id).orElse(null);
		if (assignment == null) {
			return null;
		}
		return AssignmentResponse.from(assignment);
	}

	@Transactional
	public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request) {
		Assignment assignment = assignmentRepository.findById(id).orElse(null);
		if (assignment == null) {
			return null;
		}
		assignment.updateInfo(request.title(), request.description());
		Assignment saved = assignmentRepository.save(assignment);
		return AssignmentResponse.from(saved);
	}

	@Transactional
	public boolean deleteAssignment(Long id) {
		if (!assignmentRepository.existsById(id)) {
			return false;
		}
		assignmentRepository.deleteById(id);
		return true;
	}
}
