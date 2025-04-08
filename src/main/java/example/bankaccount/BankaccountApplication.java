package example.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AnnotationTemplateExpressionDefaults;

@SpringBootApplication
@EnableMethodSecurity
public class BankaccountApplication {

	@Bean
	AnnotationTemplateExpressionDefaults templateExpressionDefaults() {
		return new AnnotationTemplateExpressionDefaults();
	}

	public static void main(String[] args) {
		SpringApplication.run(BankaccountApplication.class, args);
	}

}
