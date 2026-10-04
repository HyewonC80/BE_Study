package com.likelion.pbl.member.controller;

import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.dto.LionCreateRequest;
import com.likelion.pbl.member.dto.LionUpdateRequest;
import com.likelion.pbl.member.dto.MemberResponse;
import com.likelion.pbl.member.dto.StaffCreateRequest;
import com.likelion.pbl.member.dto.StaffUpdateRequest;
import com.likelion.pbl.member.service.MemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/lions")
	public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createLion(request));
	}

	@PostMapping("/staffs")
	public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createStaff(request));
	}

	@GetMapping
	public ResponseEntity<List<MemberResponse>> getMembers() {
		List<MemberResponse> responses = memberService.findAll().stream()
			.map(MemberResponse::from)
			.toList();
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
		Member member = memberService.findById(id);
		if (member == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(MemberResponse.from(member));
	}

	@PutMapping("/lions/{id}")
	public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
		MemberResponse response = memberService.updateLion(id, request);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/staffs/{id}")
	public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
		MemberResponse response = memberService.updateStaff(id, request);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
		if (!memberService.deleteMember(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
