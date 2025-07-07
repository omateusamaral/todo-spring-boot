package io.github.com.omateusamaral.todo_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoSpringBootApplication.class, args);
	}

}
