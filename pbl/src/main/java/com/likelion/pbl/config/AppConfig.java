package com.likelion.pbl.config;

import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.repository.MemoryMemberRepository;
import com.likelion.pbl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 수동 주입 실험용 설정 클래스.
// 지금은 MemoryMemberRepository/MemberService에 @Repository, @Service가 붙어 있어 자동 주입으로 동작하는 중이라
// 아래 @Configuration을 활성화하면 Bean이 중복 등록되어 충돌한다.
// 수동 주입 방식을 다시 보고 싶다면, MemoryMemberRepository의 @Repository와 MemberService의 @Service를 지운 뒤
// 아래 주석을 해제한다.
// @Configuration
public class AppConfig {

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
}
