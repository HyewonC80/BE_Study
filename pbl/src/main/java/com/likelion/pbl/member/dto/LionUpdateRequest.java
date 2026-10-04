package com.likelion.pbl.member.dto;

public record LionUpdateRequest(
	String major,
	int generation,
	String part,
	String studentId
) {
}
