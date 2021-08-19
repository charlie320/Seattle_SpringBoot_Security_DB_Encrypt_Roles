package org.perscholas.seattle_security;

import org.perscholas.seattle_security.models.User;
import org.perscholas.seattle_security.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeattleSpringBootSecurityDbEncryptRolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeattleSpringBootSecurityDbEncryptRolesApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertEmployeeRecords(UserService userService) {
		return args -> {
			// Save two standard users (ROLE_USER)
			userService.save(new User("John", "john1234"));
			userService.save(new User("Jane", "jane1234"));
			
			// Save two admin users (ROLE_ADMIN)
			User user = null;
			user = new User("Joan", "joan1234");
			user.setUserRole("ROLE_ADMIN");
			userService.save(user);
			user = new User("James", "james1234");
			user.setUserRole("ROLE_ADMIN");
			userService.save(user);		};
	}
}
