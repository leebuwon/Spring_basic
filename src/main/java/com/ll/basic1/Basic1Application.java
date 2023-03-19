package com.ll.basic1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 이거를 넣어줘야 @EntityListeners(AuditingEntityListener.class)
public class Basic1Application {

	public static void main(String[] args) {
		SpringApplication.run(Basic1Application.class, args);
	}
}
