package example.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AnnotationTemplateExpressionDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableMethodSecurity
public class BankaccountApplication {

	@Bean
	UserDetailsService users() {
		UserDetails rob = User.withDefaultPasswordEncoder()
				.username("rob")
				.password("password")
				.roles("USER")
				.build();
		UserDetails josh = User.withDefaultPasswordEncoder()
				.username("josh")
				.password("password")
				.roles("USER")
				.build();
		UserDetails accountant = User.withDefaultPasswordEncoder()
				.username("accountant")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(rob, josh, accountant);
	}

	@Bean
	AnnotationTemplateExpressionDefaults templateExpressionDefaults() {
		return new AnnotationTemplateExpressionDefaults();
	}

	public static void main(String[] args) {
		SpringApplication.run(BankaccountApplication.class, args);
	}

}
