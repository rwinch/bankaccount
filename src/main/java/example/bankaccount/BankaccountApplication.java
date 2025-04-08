package example.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AnnotationTemplateExpressionDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableMethodSecurity
public class BankaccountApplication {

	@Bean
	SecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(r -> r
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.oneTimeTokenLogin(ott -> ott
				.tokenGenerationSuccessHandler((request, response, oneTimeToken) ->  {
					response.setContentType(MediaType.TEXT_PLAIN_VALUE);
					response.getWriter().write("You have console mail!");
					System.out.println("Log in http://localhost:8080/login/ott?token=" + oneTimeToken.getTokenValue());
				})
			);
		return http.build();
	}

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
				.roles("USER", "ACCOUNTANT")
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
