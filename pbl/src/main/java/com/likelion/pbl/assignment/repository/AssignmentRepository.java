package com.likelion.pbl.assignment.repository;

import com.likelion.pbl.assignment.domain.Assignment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

	List<Assignment> findByMemberId(Long memberId);
}
