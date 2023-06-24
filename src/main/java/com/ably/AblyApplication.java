package com.ably;

import com.ably.dto.user.request.SignUpRequest;
import com.ably.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class AblyApplication {

    private final MemberService memberService;

    public static void main(String[] args) {
        SpringApplication.run(AblyApplication.class, args);
    }

    @Bean
    public void initDataSet(){
        memberService.signUp(SignUpRequest.builder()
                .email("carrey@naver.com")
                .password("1234")
                .build());
    }
}
