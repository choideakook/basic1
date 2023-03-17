package com.ll.basic1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 자동으로 생성, 수정 날짜값이 Entity 에 채워지는 어노테이션
public class Basic1Application {

	public static void main(String[] args) {
		SpringApplication.run(Basic1Application.class, args);
	}

}
