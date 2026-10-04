package com.likelion.pbl.assignment.controller;

import com.likelion.pbl.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl.assignment.dto.AssignmentResponse;
import com.likelion.pbl.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl.assignment.service.AssignmentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {

	private final AssignmentService assignmentService;

	public AssignmentController(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@PostMapping("/members/{memberId}/assignments")
	public ResponseEntity<AssignmentResponse> createAssignment(@PathVariable Long memberId,
			@RequestBody AssignmentCreateRequest request) {
		AssignmentResponse response = assignmentService.createAssignment(memberId, request);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/members/{memberId}/assignments")
	public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(@PathVariable Long memberId) {
		return ResponseEntity.ok(assignmentService.findByMemberId(memberId));
	}

	@GetMapping("/assignments/{id}")
	public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
		AssignmentResponse response = assignmentService.findById(id);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/assignments/{id}")
	public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable Long id,
			@RequestBody AssignmentUpdateRequest request) {
		AssignmentResponse response = assignmentService.updateAssignment(id, request);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/assignments/{id}")
	public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
		if (!assignmentService.deleteAssignment(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
