package com.likelion.pbl;

import com.likelion.pbl.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PblApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PblApplication.class, args);

		MemberService memberService = context.getBean(MemberService.class);
		System.out.println("✅ MemberService Bean 조회 성공: " + memberService.getClass().getSimpleName());
	}
}
