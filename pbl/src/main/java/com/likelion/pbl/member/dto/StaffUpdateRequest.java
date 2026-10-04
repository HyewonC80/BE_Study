package com.likelion.pbl.member.dto;

public record StaffUpdateRequest(
	String major,
	int generation,
	String part,
	String position
) {
}
